    package com.maad.tripcalculator

    import androidx.compose.runtime.Composable
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.navigation.compose.rememberNavController
    import com.maad.tripcalculator.Route.DISTANCE
    import com.maad.tripcalculator.Route.START

    //Singleton object
    object Route {
        const val START = "start"
        const val DISTANCE = "distance"
    }

    @Composable
    fun AppNavHost() {
        //Keeps track of the back stack of composables
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = START) {
            //Add the routes
            composable(route = START) { StartScreen(navController) }
            composable(route = DISTANCE) { DistanceScreen(navController) }
        }

    }

    //const val TIME = "time"

    /*composable(route = "$TIME/{km}/{x}", arguments = listOf(
                navArgument("km") { type = NavType.FloatType },
                navArgument("x") { type = NavType.IntType }
            )) {
                val km = it.arguments?.getFloat("km")!!
                Log.d("trace", "Preparing data to be passed to time screen ")
                TimeScreen(navController, km)
            }*/