package com.maad.notesapp.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.maad.notesapp.AddingNoteScreen
import com.maad.notesapp.EditingNoteScreen
import com.maad.notesapp.HomeScreen
import com.maad.notesapp.routes.Route.ADD_NOTE
import com.maad.notesapp.routes.Route.EDIT_NOTE
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
        //According to the docs: You cannot pass complex data using parcelable
        //https://developer.android.com/guide/navigation/use-graph/pass-data
        composable(
            route = "$EDIT_NOTE/{id}/{details}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType},
                navArgument("details") { type = NavType.StringType}
            )
        ) {
            val id = it.arguments?.getInt("id")!!
            val details = it.arguments?.getString("details")!!
            EditingNoteScreen(id, details, navController = navController)
        }

    }

}