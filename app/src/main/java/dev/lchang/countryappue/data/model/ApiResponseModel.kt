package dev.lchang.countryappue.data.model

data class CountryResponse(val response: List<Country>)

data class Country(
    val name: String,
    val code: String?,
    val flag: String,
)

data class TeamResponse(val response: List<TeamWrapper>)

data class TeamWrapper(val team: Team)

data class Team(
    val id: Int,
    val name: String,
    val code: String?,
    val logo: String,
    val country: String,
    val founded: Int?,
    val national: Boolean
)

