package com.maad.cookit.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maad.cookit.navigation.AppRoutes.CATEGORY_ROUTE
import com.maad.cookit.ui.category.CategoryScreen

object AppRoutes {
    const val CATEGORY_ROUTE = "category"
}

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = CATEGORY_ROUTE, modifier = modifier) {
        composable(route = CATEGORY_ROUTE) { CategoryScreen(navController) }
    }
}