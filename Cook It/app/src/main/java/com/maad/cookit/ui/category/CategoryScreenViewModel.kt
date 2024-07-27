package com.maad.cookit.ui.category

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maad.cookit.api.MealAPIService
import com.maad.cookit.model.Category
import com.maad.cookit.model.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryScreenViewModel : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories = _categories.asStateFlow()

    private val _meals = MutableStateFlow<List<Meal>>(emptyList())
    val meals = _meals.asStateFlow()

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            _categories.update {
                MealAPIService.callable.getCategories().categories
            }
        }
    }

    fun getCategoryMeals(categoryName: String){
        viewModelScope.launch(Dispatchers.IO) {
            _meals.update {
                MealAPIService.callable.getMeals(categoryName).meals
            }
        }
    }

}