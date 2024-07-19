package com.maad.counterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maad.counterapp.ui.CounterViewModel
import com.maad.counterapp.ui.theme.CounterAppTheme
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CounterAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterApp(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

//viewModel() --> Returns an existing ViewModel or creates a new one in the given owner
@Composable
fun CounterApp(modifier: Modifier = Modifier, viewModel: CounterViewModel = viewModel()) {

    //(1) --> var counter by remember { mutableIntStateOf(0) }
    //(2) --> var counter by rememberSaveable { mutableIntStateOf(0) }
    val counter by viewModel.uiState.collectAsState() //(3)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = counter.toString(),
            fontSize = 60.sp,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Blue,
                    offset = Offset(5.0f, 10.0f),
                    blurRadius = 3f
                )
            )
        )
        Button(
            onClick = {
                //(1 & 2): counter++
                viewModel.increment()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
            modifier = modifier.padding(top = 32.dp)
        ) {
            Text(text = stringResource(R.string.increment))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterAppPreview() {
    CounterApp()
}