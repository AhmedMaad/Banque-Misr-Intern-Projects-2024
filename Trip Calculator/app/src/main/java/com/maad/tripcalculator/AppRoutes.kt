package com.maad.tripcalculator

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.maad.tripcalculator.Route.DISTANCE
import com.maad.tripcalculator.Route.START
import com.maad.tripcalculator.Route.TIME

//Singleton object
object Route {
    const val START = "start"
    const val DISTANCE = "distance"
    const val TIME = "time"
}

@Composable
fun AppNavHost() {
    //Keeps track of the back stack of composables
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = START) {
        //Add the routes
        composable(route = START) { StartScreen(navController) }
        composable(route = DISTANCE) { DistanceScreen(navController) }
        composable(
            route = "$TIME/{km}",
            arguments = listOf(navArgument("km") { type = NavType.FloatType })
        ) {
            val km = it.arguments?.getFloat("km")!!
            TimeScreen(navController, km)
        }

    }

}