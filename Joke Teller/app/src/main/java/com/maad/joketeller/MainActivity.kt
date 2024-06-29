package com.maad.joketeller

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import com.maad.joketeller.ui.theme.Blue
import com.maad.joketeller.ui.theme.DarkBlue
import com.maad.joketeller.ui.theme.JokeTellerTheme
import com.maad.joketeller.ui.theme.LightBlue
import com.maad.joketeller.ui.theme.LightGreen
import com.maad.joketeller.ui.theme.LightRed

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JokeTellerTheme {
                JokeWithTextAndButton()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun JokeTellerPreview() {
    JokeTellerTheme {
        JokeWithTextAndButton()
    }
}

@Composable
private fun JokeWithTextAndButton(modifier: Modifier = Modifier) {

    val jokes = listOf(R.string.joke_1, R.string.joke_2, R.string.joke_3, R.string.joke_4)
    //The mutableStateOf() function returns an observable.
    //This means that when the value of the "randomJoke" variable changes,
    // a recomposition is triggered, and the UI refreshes.
    var randomJoke by remember {
        mutableIntStateOf(R.string.jokes_will_appear_here)
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Card(
            border = BorderStroke(
                2.dp, Brush.horizontalGradient(
                    listOf(LightRed, LightGreen)
                )
            ),
            colors = CardDefaults.cardColors(containerColor = LightBlue),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            modifier = modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.8f),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                val context = LocalContext.current
                Text(
                    text = stringResource(randomJoke),
                    fontSize = 24.sp,
                    style = TextStyle(
                        brush = Brush.linearGradient(
                            listOf(Blue, DarkBlue)
                        )
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth()

                )
                TextButton(
                    onClick = {
                        randomJoke = jokes.random()
                        Toast.makeText(
                            context,
                            getString(context, R.string.ha_ha),
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = modifier
                        .padding(top = 32.dp)
                        .border(
                            BorderStroke(
                                2.dp, brush = Brush.horizontalGradient(
                                    listOf(LightRed, LightGreen)
                                )
                            ),
                            shape = RoundedCornerShape(24.dp)
                        )

                ) {
                    Text(text = stringResource(R.string.ha_ha_me))
                }
            }
        }
    }
}