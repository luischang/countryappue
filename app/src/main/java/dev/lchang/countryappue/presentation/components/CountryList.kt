package dev.lchang.countryappue.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import dev.lchang.countryappue.data.model.CountryModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*


@Composable
fun CountryList(
    countries: List<CountryModel>,
    favorites: List<String>,
    onToggleFavorite: ((CountryModel)->Unit)? =null
) {
    LazyColumn {
        items(countries) { country ->
            val isFavorite = favorites.contains(country.name)
            Card(
                modifier =
                Modifier.fillMaxWidth().padding(8.dp)
            ){
                Row(modifier = Modifier.padding(8.dp)) {
                    Image(
                        painter = rememberAsyncImagePainter(country.flag),
                        contentDescription = country.name,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = country.name)
                        Text(text = country.ranking.toString())
                    }

                    onToggleFavorite?.let {
                        IconButton(onClick = {it(country)}) {
                            Icon(
                                imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.Star,
                                contentDescription = "Add to favorites"
                            )
                        }
                    }

                }
            }

        }
    }

}