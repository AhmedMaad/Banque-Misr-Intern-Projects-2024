package com.maad.cookit.ui.recipe

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maad.cookit.api.MealAPIService
import com.maad.cookit.model.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeScreenViewModel : ViewModel() {

    private val _meals = MutableStateFlow<List<Meal>>(emptyList())
    val meals = _meals.asStateFlow()

    private val _hasError = MutableStateFlow(false)
    val hasError = _hasError.asStateFlow()

    fun getRecipe(mealId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _meals.update {
                    MealAPIService.callable.getRecipe(mealId).meals
                }
                _hasError.update { false }
            }
            catch (e: Exception){
                _hasError.update { true }
                Log.d("trace", "Recipe Error: $e")
            }
        }
    }

}
