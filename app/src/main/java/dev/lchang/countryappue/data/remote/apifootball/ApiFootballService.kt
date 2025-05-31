package dev.lchang.countryappue.data.remote.apifootball

import dev.lchang.countryappue.data.model.CountryResponse
import dev.lchang.countryappue.data.model.TeamResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiFootballService {

    @GET("countries")
    suspend fun getCountries(): CountryResponse

    @GET("teams")
    suspend fun getTeamsByCountry(@Query("country") country: String): TeamResponse
}