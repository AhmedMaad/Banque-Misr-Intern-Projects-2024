package com.maad.notesapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.maad.notesapp.database.Note

@Composable
fun EditingNoteScreen(note: Note, modifier: Modifier = Modifier, navController: NavController) {
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
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            OutlinedButton(
                onClick = {
                    //Update the note and show a confirmation toast message,
                    //then pop (finish()) to the home page automatically
                },
                modifier = modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(text = "Update")
            }
            OutlinedButton(
                onClick = {
                    //Delete the note and show a confirmation toast message,
                    //then pop (finish()) to the home page automatically
                },
                modifier = modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(text = "Delete")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun EditingNoteScreenPreview() {
    EditingNoteScreen(note = Note(1, "X"), navController = rememberNavController())
}