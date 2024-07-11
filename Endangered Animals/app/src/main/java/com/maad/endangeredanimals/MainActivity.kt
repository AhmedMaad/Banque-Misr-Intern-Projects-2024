package com.maad.endangeredanimals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maad.endangeredanimals.data.DataSource
import com.maad.endangeredanimals.model.Animal
import com.maad.endangeredanimals.ui.theme.EndangeredAnimalsTheme
import com.maad.endangeredanimals.ui.theme.PowderBlue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EndangeredAnimalsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimalsList(DataSource().getAnimalsData())
                }
            }
        }
    }
}

@Composable
fun AnimalsList(animals: List<Animal>) {
    LazyColumn {
        items(animals) {
            AnimalsListItem(animal = it)
        }
    }
}

@Composable
fun AnimalsListItem(animal: Animal, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(containerColor = PowderBlue),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = animal.picture), contentDescription = stringResource(
                    id = animal.name
                ),
                modifier = modifier
                    .size(120.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    //Note: height is related to "weight"
                    .height(120.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = animal.name),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        //The parent will divide the vertical space remaining
                        //after measuring unweighted child elements
                        .weight(1f)
                        .wrapContentHeight(Alignment.CenterVertically)
                )
                //This text will take its default size, then the
                //above text will take the remaining space
                Text(
                    buildAnnotatedString {
                        //append(stringResource(id = R.string.save))
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(stringResource(id = R.string.save))
                        }
                        append(stringResource(id = R.string.the_animal))
                    },
                    modifier = modifier.clickable {
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ListItemPreview() {
    AnimalsListItem(animal = DataSource().getAnimalsData()[4])
}
