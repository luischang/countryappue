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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import dev.lchang.countryappue.data.local.AppDatabase
import dev.lchang.countryappue.data.local.FavoriteCountryEntity
import dev.lchang.countryappue.data.model.CountryModel
import dev.lchang.countryappue.data.repository.FavoriteRepository
import dev.lchang.countryappue.presentation.components.CountryList
import dev.lchang.countryappue.presentation.favorites.FavoriteViewModel
import dev.lchang.countryappue.presentation.favorites.FavoriteViewModelFactory


//mock data
val countries = listOf(
    CountryModel("Argentina", 1, "https://flagcdn.com/w320/ar.png"),
    CountryModel("Brasil", 2, "https://flagcdn.com/w320/br.png"),
    CountryModel("Colombia", 3, "https://flagcdn.com/w320/co.png"),
    CountryModel("España", 4, "https://flagcdn.com/w320/es.png"),
    CountryModel("México", 55, "https://flagcdn.com/w320/mx.png"),
    CountryModel("Perú", 6, "https://flagcdn.com/w320/pe.png"),
    CountryModel("Uruguay", 7, "https://flagcdn.com/w320/uy.png"),
)


@Composable
fun CountryListScreen(){
    val context = LocalContext.current
    val db = remember { AppDatabase.getInstance(context) }
    val repository = remember { FavoriteRepository(db.favoriteCountryDao()) }
    val viewModel : FavoriteViewModel = viewModel(factory = FavoriteViewModelFactory(repository))
    val favorites by viewModel.favorites.collectAsState(emptyList())

    val favoriteNames  =favorites.map { it.name }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Text("Ranking FIFA")

        CountryList(
            countries = countries,
            favorites = favorites.map { it.name },
            onToggleFavorite = { country ->
                val isFavorite = favoriteNames.contains(country.name)
                if(isFavorite){
                    favorites.find { it.name == country.name }?.let {
                        viewModel.deleteFavorite(it.id)
                    }
                }else{
                    viewModel.insertFavorite(
                        FavoriteCountryEntity(name = country.name, ranking = country.ranking, imageUrl = country.flag)
                    )
                }
            }
        )
    }
}