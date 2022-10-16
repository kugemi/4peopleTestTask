package com.kugemi.exchangeratestracker.infrastructure.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kugemi.exchangeratestracker.model.local_dto.FavoriteRate

@Dao
abstract class FavoritesDao {

    @Query("SELECT * FROM FAVORITES")
    abstract fun getFavorites(): LiveData<List<FavoriteRate>>

    @Insert
    abstract fun addFavoriteRate(rate: FavoriteRate)

    @Query("DELETE FROM FAVORITES WHERE name = :rate")
    abstract fun removeFavoriteRate(rate: String)
}