package dev.lchang.countryappue.presentation.apifootball

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lchang.countryappue.data.model.Country
import dev.lchang.countryappue.data.model.TeamWrapper
import dev.lchang.countryappue.data.remote.apifootball.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ApiFootballViewModel: ViewModel() {

    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> = _countries

    private val _selectedCountry = MutableStateFlow<Country?>(null)
    val selectedCountry: StateFlow<Country?> = _selectedCountry

    private val _teams = MutableStateFlow<List<TeamWrapper>>(emptyList())
    val teams: StateFlow<List<TeamWrapper>> = _teams

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init{
        loadCountries()
    }

    fun loadCountries(){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.getCountries()
                _countries.value = response.response.sortedBy { it.name }
                _error.value = null
            }catch (e: Exception) {
                _error.value = e.message
            }finally {
                _isLoading.value = false
            }
        }
    }

    fun onCountrySelected(country: Country) {
        _selectedCountry.value = country
        loadTeamsByCountry(country.name)
    }

    private fun loadTeamsByCountry(countryName: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.getTeamsByCountry(countryName)
                _teams.value = response.response

            }catch (e: Exception) {
                _error.value = e.message
            }finally {
                _isLoading.value = false
            }
        }
    }








}