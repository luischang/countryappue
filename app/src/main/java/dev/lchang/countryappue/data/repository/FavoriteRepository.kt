package dev.lchang.countryappue.data.repository

import dev.lchang.countryappue.data.local.FavoriteCountryDao
import dev.lchang.countryappue.data.local.FavoriteCountryEntity
import kotlinx.coroutines.flow.Flow

class FavoriteRepository(private val dao: FavoriteCountryDao)  {
    //Insert
    suspend fun insert(country: FavoriteCountryEntity) = dao.insert(country)
    //Delete
    suspend fun delete(id: Int) = dao.delete(id)
    //Delete
    suspend fun delete(country: FavoriteCountryEntity) = dao.delete(country)
    //Get all
    fun getAll(): Flow<List<FavoriteCountryEntity>> = dao.getAll()
}