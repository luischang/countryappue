package dev.lchang.countryappue.presentation.home

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import coil.compose.rememberAsyncImagePainter

data class Country(val name: String, val ranking: Int, val flag: String)

//mock data
val countries = listOf(
    Country("Argentina", 1, "https://flagcdn.com/w320/ar.png"),
    Country("Brasil", 2, "https://flagcdn.com/w320/br.png"),
    Country("Colombia", 3, "https://flagcdn.com/w320/co.png"),
    Country("España", 4, "https://flagcdn.com/w320/es.png"),
    Country("México", 55, "https://flagcdn.com/w320/mx.png"),
    Country("Perú", 6, "https://flagcdn.com/w320/pe.png"),
    Country("Uruguay", 7, "https://flagcdn.com/w320/uy.png"),
)


@Composable
fun CountryListScreen(){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Text("Ranking FIFA")

        LazyColumn{
            items(countries){ country ->
                //Add card
                Card(modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(vertical = 8.dp))
                {
                    Row(modifier = Modifier.padding(16.dp))
                    {
                        Image(
                            painter = rememberAsyncImagePainter(country.flag),
                            contentDescription = country.name,
                            modifier = Modifier.size(50.dp)
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                        Column {
                            Text(text = country.name, style = MaterialTheme.typography.titleMedium)
                            Text(text = country.ranking.toString())
                        }
                    }
                }
            }
        }



    }

}