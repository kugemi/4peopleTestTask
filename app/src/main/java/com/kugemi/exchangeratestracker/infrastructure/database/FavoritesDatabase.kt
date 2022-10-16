package com.kugemi.exchangeratestracker.infrastructure.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kugemi.exchangeratestracker.model.local_dto.FavoriteRate

@Database(entities = [FavoriteRate::class], version = 1)
abstract class FavoritesDatabase : RoomDatabase() {

    abstract fun Dao(): FavoritesDao
}