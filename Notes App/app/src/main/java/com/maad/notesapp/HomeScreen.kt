package com.maad.notesapp

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.maad.notesapp.database.Note
import com.maad.notesapp.routes.Route.ADD_NOTE
import com.maad.notesapp.ui.NoteViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel = viewModel(),
) {
    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(ADD_NOTE)
                },
                containerColor = Color.Blue,
            ) {
                Icon(
                    Icons.Filled.Add,
                    "Add note page button",
                    tint = Color.White,
                )
            }
        }
    ) { _ ->
        val notes by viewModel.getNotes().collectAsState(initial = emptyList())

        LazyColumn(modifier = modifier.padding(top = 60.dp)) {
            items(notes) { note ->
                NoteListItem(note = note)
            }
        }
    }
}

@Composable
fun NoteListItem(note: Note, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = note.noteDetails,
            fontSize = 20.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(8.dp)
                .defaultMinSize(minHeight = 80.dp)
                .wrapContentHeight(Alignment.CenterVertically)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    NoteListItem(note = Note(5, "Hello this is a test note"))
}