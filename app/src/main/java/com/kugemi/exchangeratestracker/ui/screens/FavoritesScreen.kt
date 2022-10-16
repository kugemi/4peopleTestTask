package com.kugemi.exchangeratestracker.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kugemi.exchangeratestracker.ui.components.RateItem
import com.kugemi.exchangeratestracker.viewmodels.ExchangeRatesViewModel
import com.kugemi.exchangeratestracker.viewmodels.FavoriteRatesViewModel

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    exchangeViewModel: ExchangeRatesViewModel = viewModel(),
    favoriteRatesViewModel: FavoriteRatesViewModel = viewModel()
) {
    val rates = exchangeViewModel.rates.observeAsState()

    val favoriteRates = favoriteRatesViewModel.favoriteRates.observeAsState()

    rates.value.let { rateItems ->
        rateItems?.let { list ->
            val filteredList = list.filter {
                var isContains = false
                favoriteRates.value?.forEach { favorite ->
                    if (it.name == favorite.name) isContains = true
                }
                isContains
            }

            LazyColumn() {
                items(filteredList) { item ->
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