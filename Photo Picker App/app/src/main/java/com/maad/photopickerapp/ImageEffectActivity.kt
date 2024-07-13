package com.maad.photopickerapp

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.maad.photopickerapp.ui.theme.PhotoPickerAppTheme

class ImageEffectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhotoPickerAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val context = LocalContext.current
                    val effect = intent.getStringExtra("effect")
                    val photo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                        intent.getParcelableExtra("photo", Uri::class.java)!!
                    else
                        intent.getParcelableExtra("photo")!!

                    if (effect == "bw")
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = photo,
                                imageLoader = ImageLoader(context)
                            ),
                            contentDescription = stringResource(R.string.photo),
                            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                                setToSaturation(
                                    0f
                                )
                            }),
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        )
                    else {
                        val contrast = 2f // 0f..10f (1 should be default)
                        val brightness = -180f // -255f..255f (0 should be default)
                        val colorMatrix = floatArrayOf(
                            contrast, 0f, 0f, 0f, brightness,
                            0f, contrast, 0f, 0f, brightness,
                            0f, 0f, contrast, 0f, brightness,
                            0f, 0f, 0f, 1f, 0f
                        )
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = photo,
                                imageLoader = ImageLoader(context)
                            ),
                            contentDescription = stringResource(R.string.photo),
                            colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        )
                    }

                }
            }
        }
    }
}
