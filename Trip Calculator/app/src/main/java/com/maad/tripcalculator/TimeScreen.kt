package com.maad.tripcalculator

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun TimeScreen(navController: NavController, km: Float, modifier: Modifier = Modifier) {
   Log.d("trace", "KM: $km")
   Text(text = km.toString())
}


@Preview(showBackground = true)
@Composable
fun TimeScreenPreview() {

}