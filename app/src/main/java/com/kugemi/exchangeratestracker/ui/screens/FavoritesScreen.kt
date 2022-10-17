package com.kugemi.exchangeratestracker.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kugemi.exchangeratestracker.data.enums.resources.bottomBarHeight
import com.kugemi.exchangeratestracker.ui.components.RateItem
import com.kugemi.exchangeratestracker.utils.extensions.sortedBySortType
import com.kugemi.exchangeratestracker.viewmodels.ExchangeRatesViewModel
import com.kugemi.exchangeratestracker.viewmodels.FavoriteRatesViewModel

@Composable
fun FavoritesScreen(
    exchangeViewModel: ExchangeRatesViewModel,
    favoriteRatesViewModel: FavoriteRatesViewModel
) {
    val currentRate = exchangeViewModel.currentRate.observeAsState()

    currentRate.value?.let { rate ->
        exchangeViewModel.updateRates(rate)
    }

    val rates = exchangeViewModel.rates.observeAsState()

    val favoriteRates = favoriteRatesViewModel.favoriteRates.observeAsState()

    val sortType = exchangeViewModel.sortType.observeAsState()

    var isRatesEmpty by remember { mutableStateOf(rates.value.isNullOrEmpty()) }

    if (isRatesEmpty) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    rates.value?.let { list ->
        sortType.value?.let { sortType ->
            val filteredList = list.filter {
                var isContains = false
                favoriteRates.value?.forEach { favorite ->
                    if (it.name == favorite.name) isContains = true
                }
                isContains
            }

            isRatesEmpty = false

            LazyColumn(modifier = Modifier.padding(bottom = bottomBarHeight)) {
                items(filteredList.sortedBySortType(sortType)) { item ->
                    RateItem(
                        name = item.name,
                        value = item.value,
                        favoriteRatesViewModel
                    )
                }
            }
        }
    }
}