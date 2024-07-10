package com.maad.endangeredanimals.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Animal(
    @StringRes val name: Int,
    @StringRes val description: Int,
    @DrawableRes val picture: Int,
    val link: String,
)
