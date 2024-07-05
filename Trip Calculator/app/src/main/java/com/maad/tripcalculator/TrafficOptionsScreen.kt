package com.maad.tripcalculator

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun TrafficOptionsScreen(navController: NavController, km: Float, time: Int, modifier: Modifier = Modifier) {
    Log.d("trace", "km: $km")
    Log.d("trace", "time: $time")
    Text(text = "$km $time")
}

@Preview
@Composable
fun TrafficOptionsScreenPreview() {

}