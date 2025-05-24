package dev.lchang.countryappue.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCountryDao {
    @Insert
    suspend fun insert(country: FavoriteCountryEntity)

    @Query("SELECT * FROM favorite_countries")
    fun getAll(): Flow<List<FavoriteCountryEntity>>

    @Query("DELETE FROM favorite_countries WHERE id = :id")
    suspend fun delete(id: Int)

    @Delete
    suspend fun delete(country: FavoriteCountryEntity)
}