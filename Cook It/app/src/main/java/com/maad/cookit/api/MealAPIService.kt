package com.maad.cookit.api

import com.maad.cookit.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MealAPIService {

    private val retrofit = Retrofit
        .Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //Lazy properties: the value is computed "only" on first access
    val callable: MealAPICallable by lazy {
        retrofit.create(MealAPICallable::class.java)
    }

}