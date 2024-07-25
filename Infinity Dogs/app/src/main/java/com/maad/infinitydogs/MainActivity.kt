package com.maad.infinitydogs

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import com.maad.infinitydogs.api.Dog
import com.maad.infinitydogs.api.DogAPICallable
import com.maad.infinitydogs.ui.theme.InfinityDogsTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InfinityDogsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DogImage(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

}

@Composable
fun DogImage(modifier: Modifier = Modifier) {
    var link by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        getImage(
            onImageReceived = { link = it },
            onFailure = {
                val toastMessage = if (it.message!!.contains("Unable to resolve host"))
                    "Check your connection"
                else
                    "Failed to load image"
                Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT)
                    .show()
            }
        )
    }

    AsyncImage(
        model = ImageRequest
            .Builder(context)
            .data(link)
            .crossfade(true)
            .crossfade(700)
            .error(R.drawable.ic_broken_image)
            .build(),
        //model = link,
        contentDescription = "Dog image",
        //To execute image requests
        imageLoader = context.imageLoader,
        modifier = modifier.fillMaxSize()
    )
}

private fun getImage(
    onImageReceived: (String?) -> Unit,
    onFailure: (Throwable) -> Unit
) {
    val retrofit = Retrofit
        .Builder()
        .baseUrl("https://dog.ceo")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val c = retrofit.create(DogAPICallable::class.java)
    c.getImage().enqueue(object : Callback<Dog> {
        override fun onResponse(call: Call<Dog>, response: Response<Dog>) {
            val image = response.body()?.imageLink
            if (image != null)
                onImageReceived(image)
            else
                onImageReceived(null)
        }

        override fun onFailure(call: Call<Dog>, t: Throwable) {
            Log.d("trace", "Error: ${t.message}")
            onFailure(t)
        }
    })
}