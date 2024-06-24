package com.maad.profilecardlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maad.profilecardlayout.ui.theme.ProfileCardLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileCardLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileCard(
                        "Ahmed Atef",
                        "Android Developer",
                        "maad@gmail.com",
                        "+201111111111",
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileCard(
    name: String,
    job: String,
    email: String,
    phone: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.paint(
            painterResource(id = R.drawable.profile_bg),
            contentScale = ContentScale.Crop,
            alpha = 0.4f
        ),
        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "profile picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .border(
                    BorderStroke(4.dp, Color.Black),
                    CircleShape
                )
                .background(color = Color.DarkGray, shape = CircleShape)
                .padding(4.dp)
                .clip(CircleShape)
        )
        Text(
            text = name,
            fontSize = 50.sp,
        )

        Text(
            text = job,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                text = email,
                modifier = Modifier.padding(end = 16.dp),
                textDecoration = TextDecoration.Underline
            )
            Text(
                text = phone,
                textDecoration = TextDecoration.Underline
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardPreview() {
    ProfileCardLayoutTheme {
        ProfileCard(
            "Ahmed Atef",
            "Android Developer",
            "maad@gmail.com",
            "+201111111111",
            modifier = Modifier.fillMaxSize()
        )
    }
}