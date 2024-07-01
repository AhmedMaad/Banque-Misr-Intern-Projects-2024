package com.maad.tripcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.maad.tripcalculator.ui.theme.TripCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TripCalculatorTheme {
                StartScreen()
            }
        }
    }
}

