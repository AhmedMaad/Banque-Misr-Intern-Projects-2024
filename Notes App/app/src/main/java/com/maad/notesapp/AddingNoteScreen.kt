package com.maad.notesapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun AddingNoteScreen(modifier: Modifier = Modifier, navController: NavController) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
    ) {

        var details by remember { mutableStateOf("") }

        OutlinedTextField(
            value = details,
            onValueChange = { details = it },
            label = {
                Text(text = "Note Details")
            },
            modifier = modifier.fillMaxWidth()
        )

        OutlinedButton(
            onClick = {
                //Save note to database with a confirmation
                //toast message
            },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp)
        ) {
            Text(text = "Save")
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun AddingNoteScreenPreview() {
    AddingNoteScreen(navController = rememberNavController())
}