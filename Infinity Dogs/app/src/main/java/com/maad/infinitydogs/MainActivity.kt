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
import coil.compose.AsyncImage
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
    var dog by remember { mutableStateOf<Dog?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        getDogImage {
            if (it != null)
                dog = it
            else
                Toast.makeText(context, "Failed to load image", Toast.LENGTH_SHORT).show()
            isLoading = false
        }
    }
    if (!isLoading) {
        AsyncImage(
            model = ImageRequest
                .Builder(context)
                .data(dog?.imageLink)
                .crossfade(700)
                .placeholder(R.drawable.ic_downloading)
                .error(R.drawable.ic_broken_image)
                .build(),
            //model = link,
            //imageLoader = context.imageLoader,
            contentDescription = "Dog image",
            modifier = modifier.fillMaxSize()
        )
    }

}


private fun getDogImage(
    onDogReceived: (Dog?) -> Unit,
) {
    val retrofit = Retrofit
        .Builder()
        .baseUrl("https://dog.ceo")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val c = retrofit.create(DogAPICallable::class.java)
    c.getImage().enqueue(object : Callback<Dog> {
        override fun onResponse(call: Call<Dog>, response: Response<Dog>) {
            val dog = response.body()
            onDogReceived(dog)
        }

        override fun onFailure(call: Call<Dog>, t: Throwable) {
            Log.d("trace", "Error: ${t.message}")
            onDogReceived(null)
        }
    })
}