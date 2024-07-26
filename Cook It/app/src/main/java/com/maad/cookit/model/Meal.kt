package com.maad.cookit.model

import com.google.gson.annotations.SerializedName
import com.maad.cookit.constants.Constants.MEAL_ID
import com.maad.cookit.constants.Constants.MEAL_IMAGE
import com.maad.cookit.constants.Constants.MEAL_INSTRUCTIONS
import com.maad.cookit.constants.Constants.MEAL_NAME
import com.maad.cookit.constants.Constants.MEAL_VIDEO

data class MealRoot(val meals: List<Meal>)

data class Meal(
    @SerializedName(MEAL_ID)
    val id: String,
    @SerializedName(MEAL_NAME)
    val name: String,
    @SerializedName(MEAL_IMAGE)
    val image: String,

    @SerializedName(MEAL_INSTRUCTIONS)
    val instructions: String,
    @SerializedName(MEAL_VIDEO)
    val video: String
)