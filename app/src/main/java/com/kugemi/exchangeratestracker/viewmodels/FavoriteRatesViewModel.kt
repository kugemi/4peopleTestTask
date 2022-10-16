package com.kugemi.exchangeratestracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kugemi.exchangeratestracker.infrastructure.repositories.interfaces.IFavoriteRatesRepository
import com.kugemi.exchangeratestracker.model.local_dto.FavoriteRate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteRatesViewModel @Inject constructor(private val repository: IFavoriteRatesRepository)
    : ViewModel() {

    val favoriteRates = repository.getFavoritesRates()

    fun addFavoriteRate(rate: FavoriteRate) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFavoriteRate(rate)
        }
    }

    fun removeFavoriteRate(rate: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFavorite(rate)
        }
    }
}