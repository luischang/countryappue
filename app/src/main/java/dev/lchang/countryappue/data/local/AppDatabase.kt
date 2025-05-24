package dev.lchang.countryappue.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteCountryEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun favoriteCountryDao(): FavoriteCountryDao

    companion object{
        const val DATABASE_NAME = "country_database"
        @Volatile
        private var INSTANCE : AppDatabase?=null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build().also { INSTANCE = it }
            }
        }
    }
}