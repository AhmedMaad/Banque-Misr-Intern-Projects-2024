package com.maad.notesapp.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maad.notesapp.AddingNoteScreen
import com.maad.notesapp.HomeScreen
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
        /*composable(
            route = "$HOME/{note}",
            arguments = listOf(navArgument("note") { type = NavType. })
        ) {
            val km = it.arguments?.getParcelable("note", Note::class.java)!!
            EditingNoteScreen(navController, km)
        }*/

    }

}