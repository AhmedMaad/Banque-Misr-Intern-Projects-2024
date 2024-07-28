package com.maad.cookit.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.maad.cookit.constants.Constants.MEAL_ID
import com.maad.cookit.navigation.AppRoutes.CATEGORY_ROUTE
import com.maad.cookit.navigation.AppRoutes.RECIPE_ROUTE
import com.maad.cookit.ui.category.CategoryScreen
import com.maad.cookit.ui.recipe.RecipeScreen

object AppRoutes {
    const val CATEGORY_ROUTE = "category"
    const val RECIPE_ROUTE = "recipe"
}

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = CATEGORY_ROUTE, modifier = modifier) {
        composable(route = CATEGORY_ROUTE) { CategoryScreen(navController) }
        composable(
            route = "$RECIPE_ROUTE/{$MEAL_ID}",
            arguments = listOf(navArgument(MEAL_ID) { type = NavType.StringType })
        ) {
            val mealId = it.arguments?.getString(MEAL_ID)!!
            RecipeScreen(mealId, modifier)
        }
    }

}