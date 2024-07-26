package com.maad.cookit.model

import com.google.gson.annotations.SerializedName
import com.maad.cookit.constants.Constants.CATEGORY_IMAGE
import com.maad.cookit.constants.Constants.CATEGORY_NAME

data class CategoryRoot(val categories: List<Category>)

data class Category(
    @SerializedName(CATEGORY_NAME)
    val name: String,
    @SerializedName(CATEGORY_IMAGE)
    val image: String,
)