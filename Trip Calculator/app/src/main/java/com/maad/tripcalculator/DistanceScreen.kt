package com.maad.tripcalculator

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.maad.tripcalculator.Route.TIME

@Composable
fun DistanceScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = stringResource(R.string.enter_the_total_distance))

        var distanceField by remember { mutableStateOf("0") }
        OutlinedTextField(
            //The value of the text field is set to the "remembered" value
            value = distanceField,
            //'it' contains the new value
            onValueChange = { distanceField = it },
            label = {
                Text(text = stringResource(R.string.distance_in_kilometers))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),

            modifier = modifier
                .fillMaxWidth()
                .padding(all = 32.dp)
        )
        Button(onClick = {
            val distance = distanceField.toDouble()
            navController.navigate("$TIME/$distance")
        }) {
            Text(text = stringResource(R.string.next))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DistanceScreenPreview() {
    DistanceScreen(rememberNavController())
}