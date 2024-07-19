package com.maad.counterapp.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(0)

    //"asStateFlow()" makes this mutable state flow read-only
    val uiState = _uiState.asStateFlow()

    fun increment() {
        _uiState.update { it + 1 }
    }

}