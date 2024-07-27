package com.maad.cookit.ui.category

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.maad.cookit.model.Category

@Composable
fun CategoryScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CategoryScreenViewModel = viewModel()
) {
    val categories by viewModel.categories.collectAsState()
    Log.d("trace", "Received categories: $categories")

    LazyRow {
        items(categories) { category ->
            CategoryListItem(category = category)
        }
    }

}

@Composable
fun CategoryListItem(category: Category, modifier: Modifier = Modifier) {
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
                .size(120.dp)
                .clip(RoundedCornerShape(60.dp))
                .border(
                    BorderStroke(4.dp, rainbowColorsBrush),
                    CircleShape
                )
                .clickable {
                    
                }
        )
        Text(
            text = category.name,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(top = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryListItemPreview() {
    CategoryListItem(
        Category(
            "Miscellaneous",
            "https://www.themealdb.com/images/category/beef.png"
        )
    )
}