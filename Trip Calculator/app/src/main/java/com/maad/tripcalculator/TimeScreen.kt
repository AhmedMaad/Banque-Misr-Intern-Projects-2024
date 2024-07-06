package com.maad.tripcalculator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.maad.tripcalculator.Route.TRAFFIC_OPTIONS

@Composable
fun TimeScreen(navController: NavController, km: Float, modifier: Modifier = Modifier) {
    DataEntry(
        text = R.string.enter_the_total_time,
        fieldLabel = R.string.time_in_minutes,
        isTimeInput = true,
        onClick = { timeField ->
            val time = timeField.toInt()
            navController.navigate("${TRAFFIC_OPTIONS}/$km/$time")
        },
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun TimeScreenPreview() {
    TimeScreen(rememberNavController(), 12.6f)
}