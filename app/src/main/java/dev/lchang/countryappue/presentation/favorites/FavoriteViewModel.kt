package dev.lchang.countryappue.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lchang.countryappue.data.local.FavoriteCountryEntity
import dev.lchang.countryappue.data.repository.FavoriteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: FavoriteRepository): ViewModel() {

    val favorites: StateFlow<List<FavoriteCountryEntity>> =
        repository
            .getAll()
            .stateIn(viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList())

    fun insertFavorite(country: FavoriteCountryEntity){
        viewModelScope.launch {
            repository.insert(country)
        }
    }
    fun deleteFavorite(id: Int){
        viewModelScope.launch {
            repository.delete(id)
        }
    }
    fun deleteFavorite(country: FavoriteCountryEntity){
        viewModelScope.launch {
            repository.delete(country)
        }
    }
}