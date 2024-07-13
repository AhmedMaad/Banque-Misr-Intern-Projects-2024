package com.maad.photopickerapp

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.maad.photopickerapp.ui.theme.PhotoPickerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhotoPickerAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PhotoPicker(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun PhotoPicker(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var picUri by remember { mutableStateOf<Uri?>(null) }
    val resultLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        picUri = it
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        //* means all formats (e.g. PNG, JPEG, ..., etc.)
        Button(onClick = { resultLauncher.launch("image/*") }) {
            Text(text = stringResource(R.string.pick_a_photo))
        }
        Image(
            painter = rememberAsyncImagePainter(
                model = picUri,
                imageLoader = ImageLoader(context)
            ),
            contentDescription = stringResource(R.string.photo),
            modifier = modifier
                .width(250.dp)
                .height(250.dp)
        )
        ButtonEffect(
            context = context,
            effect = "bw",
            picUri = picUri,
            btnText = R.string.black_and_white
        )
        ButtonEffect(
            context = context,
            effect = "bc",
            picUri = picUri,
            btnText = R.string.brightness_and_contrast
        )
    }

}

@Composable
fun ButtonEffect(context: Context, effect: String, picUri: Uri?, @StringRes btnText: Int) {
    Button(onClick = {
        if (picUri != null) {
            val i = Intent(context, ImageEffectActivity::class.java)
            i.putExtra("effect", effect)
            i.putExtra("photo", picUri)
            context.startActivity(i)
        } else
            Toast.makeText(
                context,
                R.string.choose_an_image_before_selecting_an_effect,
                Toast.LENGTH_LONG
            ).show()

    }) {
        Text(text = stringResource(id = btnText))
    }

}

@Preview(showBackground = true)
@Composable
fun PhotoPickerPreview() {
    PhotoPicker()
}