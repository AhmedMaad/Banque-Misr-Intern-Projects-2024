package com.maad.cookit.api

import com.maad.cookit.constants.Constants.CATEGORIES_ENDPOINT
import com.maad.cookit.constants.Constants.CATEGORY_QUERY
import com.maad.cookit.constants.Constants.FILTER_ENDPOINT
import com.maad.cookit.constants.Constants.INGREDIENT_QUERY
import com.maad.cookit.constants.Constants.LOOKUP_ENDPOINT
import com.maad.cookit.model.CategoryRoot
import com.maad.cookit.model.MealRoot
import retrofit2.http.GET
import retrofit2.http.Query

interface MealAPICallable {

    @GET(CATEGORIES_ENDPOINT)
    suspend fun getCategories(): CategoryRoot

    @GET(FILTER_ENDPOINT)
    fun getMeals(@Query(CATEGORY_QUERY) categoryName: String): MealRoot

    @GET(LOOKUP_ENDPOINT)
    fun getRecipe(@Query(INGREDIENT_QUERY) mealId: String): MealRoot

}