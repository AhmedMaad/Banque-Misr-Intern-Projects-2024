package com.maad.tripcalculator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.maad.tripcalculator.Route.TIME

@Composable
fun DistanceScreen(navController: NavController, modifier: Modifier = Modifier) {
    DataEntry(
        text = R.string.enter_the_total_distance,
        fieldLabel = R.string.distance_in_kilometers,
        onClick = { distanceField ->
            val distance = distanceField.toDouble()
            navController.navigate("$TIME/$distance")
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DistanceScreenPreview() {
    DistanceScreen(rememberNavController())
}