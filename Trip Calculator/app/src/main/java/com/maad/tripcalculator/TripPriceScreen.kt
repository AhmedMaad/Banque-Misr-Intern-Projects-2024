package com.maad.tripcalculator

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun TripPriceScreen(
    navController: NavController,
    km: Float,
    time: Int,
    traffic: Float,
    modifier: Modifier = Modifier
) {
    Log.d("trace", "KM: $km, TIME: $time, TRAFFIC: $traffic")
}