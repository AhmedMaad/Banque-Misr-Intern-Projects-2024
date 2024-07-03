package com.maad.tripcalculator

import android.media.MediaPlayer
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StartScreen(modifier: Modifier = Modifier) {

    var isAnimated by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = isAnimated, label = "translation")
    val screenWidth = LocalConfiguration.current.screenWidthDp.toFloat()
    val animationDuration = 300 //Calculated in Millis
    val context = LocalContext.current

    //Offset: It tells you how far something is shifted from a starting position
    val carOffset by transition.animateOffset(
        transitionSpec = { tween(animationDuration) },
        label = "car",
    ) { animated ->
        if (animated) Offset(screenWidth / 2, 0f) else Offset(screenWidth, 0f)
    }

    val roadOffset by transition.animateOffset(
        transitionSpec = { tween(animationDuration) },
        label = "road"
    ) { animated ->
        if (animated) Offset(0f, 0f) else Offset(-300f, 0f)
    }

    LaunchedEffect(Unit) {
        isAnimated = true
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.road),
            contentDescription = stringResource(id = R.string.road),
            modifier = modifier
                .offset(roadOffset.x.dp, roadOffset.y.dp)
                .size(300.dp)
        )
        Button(
            onClick = {
                MediaPlayer
                    .create(context, R.raw.car_horn)
                    .start()
                //Navigate to "DistanceScreen"
                      },
            modifier = modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(R.string.start))
        }
        Image(
            painter = painterResource(id = R.drawable.car),
            contentDescription = stringResource(id = R.string.car),
            modifier = modifier
                .offset(carOffset.x.dp, carOffset.y.dp)
                .size(300.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun StartScreenPreview() {
    StartScreen()
}