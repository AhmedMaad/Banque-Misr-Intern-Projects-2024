package com.maad.cookit.ui.category

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.maad.cookit.constants.Constants
import com.maad.cookit.model.Category
import com.maad.cookit.model.Meal
import com.maad.cookit.navigation.AppRoutes
import org.jetbrains.annotations.Async

@Composable
fun CategoryScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CategoryScreenViewModel = viewModel()
) {
    val categories by viewModel.categories.collectAsState()
    Log.d("trace", "Received categories: $categories")

    val meals by viewModel.meals.collectAsState()
    Log.d("trace", "Received Meals: $meals")

    val hasError by viewModel.hasError.collectAsState()
    if (hasError)
        Toast.makeText(LocalContext.current, "Check your connection", Toast.LENGTH_SHORT).show()

    Column(
        modifier = modifier.padding(top = 32.dp)
    ) {
        LazyRow {
            items(categories) { category ->
                MainCategoryListItem(category = category) {
                    viewModel.getCategoryMeals(category.name)
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 32.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(meals) { meal ->
                CategoryMealListItem(meal = meal) {
                    navController.navigate("${AppRoutes.RECIPE_ROUTE}/${meal.id}")
                }
            }
        }
    }


}

@Composable
fun MainCategoryListItem(
    category: Category,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(8.dp)
    ) {

        val rainbowColorsBrush =
            Brush.sweepGradient(
                listOf(
                    Color(0xFF9575CD),
                    Color(0xFFBA68C8),
                    Color(0xFFE57373),
                    Color(0xFFFFB74D),
                    Color(0xFFFFF176),
                    Color(0xFFAED581),
                    Color(0xFF4DD0E1),
                    Color(0xFF9575CD)
                )
            )

        AsyncImage(
            model = category.image,
            contentDescription = category.name,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(100.dp)
                .clip(RoundedCornerShape(50.dp))
                .border(
                    BorderStroke(4.dp, rainbowColorsBrush),
                    CircleShape
                )
                .clickable { onClick() }
        )
        Text(
            text = category.name,
            fontFamily = FontFamily.Monospace,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun CategoryMealListItem(
    meal: Meal,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .width(180.dp)
            .height(200.dp)
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Card(
            modifier = modifier
                .height(150.dp)
                .width(172.dp)
                .padding(8.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = if (meal.name.length < 20)
                    meal.name
                else
                    "${meal.name.dropLast(meal.name.length - 20)}...",
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .wrapContentHeight(Alignment.Bottom)
            )
        }
        AsyncImage(
            model = meal.image,
            contentDescription = meal.name,
            modifier = modifier
                .width(100.dp)
                .height(100.dp)
                .align(Alignment.TopCenter)
                .clip(RoundedCornerShape(12.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryListItemPreview() {
    Column {
        MainCategoryListItem(
            Category(
                "Miscellaneous",
                ""
            )
        ) {}
        CategoryMealListItem(
            meal = Meal(
                "123",
                "Bread and another bread to make some bread",
                "",
                "",
                ""
            )
        ) {}
    }

}