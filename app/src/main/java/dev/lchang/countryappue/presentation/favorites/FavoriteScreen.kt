package dev.lchang.countryappue.presentation.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.lchang.countryappue.data.local.AppDatabase
import dev.lchang.countryappue.data.model.CountryModel
import dev.lchang.countryappue.data.repository.FavoriteRepository
import dev.lchang.countryappue.presentation.components.CountryList

@Composable
fun FavoriteScreen() {
    val context = LocalContext.current
    val db = remember { AppDatabase.getInstance(context) }
    val repository = remember { FavoriteRepository(db.favoriteCountryDao()) }
    val viewModel : FavoriteViewModel = viewModel(factory = FavoriteViewModelFactory(repository))

    val favorites by viewModel.favorites.collectAsState()

    val mappedCountries = favorites.map {
        CountryModel(name = it.name, it.ranking, it.imageUrl)
    }

    CountryList(countries = mappedCountries, favorites = favorites.map { it.name })

}