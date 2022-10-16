package com.kugemi.exchangeratestracker.infrastructure.repositories.interfaces

import androidx.lifecycle.LiveData
import com.kugemi.exchangeratestracker.model.local_dto.FavoriteRate

interface IFavoriteRatesRepository {

    fun getFavoritesRates(): LiveData<List<FavoriteRate>>

    suspend fun addFavoriteRate(rate: FavoriteRate)

    suspend fun removeFavorite(rate: String)
}