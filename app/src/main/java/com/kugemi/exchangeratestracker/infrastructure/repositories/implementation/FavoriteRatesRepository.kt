package com.kugemi.exchangeratestracker.infrastructure.repositories.implementation

import androidx.lifecycle.LiveData
import com.kugemi.exchangeratestracker.infrastructure.database.FavoritesDatabase
import com.kugemi.exchangeratestracker.infrastructure.repositories.interfaces.IFavoriteRatesRepository
import com.kugemi.exchangeratestracker.model.local_dto.FavoriteRate

class FavoriteRatesRepository(private val database: FavoritesDatabase) : IFavoriteRatesRepository {
    override fun getFavoritesRates(): LiveData<List<FavoriteRate>> {
        return database.Dao().getFavorites()
    }

    override suspend fun addFavoriteRate(rate: FavoriteRate) {
        return database.Dao().addFavoriteRate(rate)
    }

    override suspend fun removeFavorite(rate: String) {
        return database.Dao().removeFavoriteRate(rate)
    }
}