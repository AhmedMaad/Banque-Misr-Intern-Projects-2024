package com.maad.tripcalculator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun TimeScreen(navController: NavController, km: Float, modifier: Modifier = Modifier) {
    DataEntry(
        text = R.string.enter_the_total_time,
        fieldLabel = R.string.time_in_minutes,
        isTimeInput = true,
        onClick = { timeField ->
            val time = timeField.toInt()
            navController.navigate("${Route.TRAFFIC_OPTIONS}/$km/$time")
        }
    )
}


@Preview(showBackground = true)
@Composable
fun TimeScreenPreview() {
    TimeScreen(rememberNavController(), 12.5f)
}