package com.maad.cookit.ui.recipe

import android.content.Intent
import android.graphics.Paint.Align
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maad.cookit.R
import com.maad.cookit.model.Meal

@Composable
fun RecipeScreen(
    mealId: String,
    modifier: Modifier = Modifier,
    viewModel: RecipeScreenViewModel = viewModel()
) {
    viewModel.getRecipe(mealId)
    val meals by viewModel.meals.collectAsState()
    Log.d("trace", "Recipe: $meals")

    val hasError by viewModel.hasError.collectAsState()
    if (hasError)
        Toast.makeText(LocalContext.current, "Check your connection", Toast.LENGTH_SHORT).show()
    else if (meals.isNotEmpty()) {
        val meal = meals[0]
        RecipeContent(meal = meal)
    }

}

@Composable
fun RecipeContent(meal: Meal, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val context = LocalContext.current
        Text(
            text = meal.name,
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            lineHeight = 48.sp,
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp, start = 16.dp, end = 16.dp)
        )
        Text(
            text = meal.instructions,
            modifier = modifier.padding(all = 16.dp)
        )
        if (meal.video.isNotEmpty()) {
            IconButton(
                onClick = {
                    val i = Intent(Intent.ACTION_VIEW, meal.video.toUri())
                    context.startActivity(i)
                },
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_video),
                    contentDescription = "Recipe Video",
                    //Disable default tint used by the theme
                    tint = Color.Unspecified
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun RecipeScreenPreview() {
    RecipeContent(
        Meal(
            "1",
            "android development is cool",
            "",
            "A lot of details will appear here",
            ""
        )
    )
}