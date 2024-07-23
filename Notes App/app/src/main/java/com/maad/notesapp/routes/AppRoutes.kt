package com.maad.notesapp.routes

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.maad.notesapp.AddingNoteScreen
import com.maad.notesapp.EditingNoteScreen
import com.maad.notesapp.HomeScreen
import com.maad.notesapp.database.Note
import com.maad.notesapp.routes.Route.ADD_NOTE
import com.maad.notesapp.routes.Route.HOME

object Route {
    const val HOME = "home"
    const val ADD_NOTE = "add_note"
    const val EDIT_NOTE = "edit_note"
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HOME) {
        composable(route = HOME) { HomeScreen(navController = navController) }
        composable(route = ADD_NOTE) { AddingNoteScreen(navController = navController) }
        composable(
            route = "$HOME/{note}",
            arguments = listOf(navArgument("note") {
                type = NavType.ParcelableType(Note::class.java)
            })
        ) {
            val note = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                it.arguments?.getParcelable("note", Note::class.java)!!
            else
                it.arguments?.getParcelable("note")!!

            EditingNoteScreen(note, navController = navController)
        }

    }

}