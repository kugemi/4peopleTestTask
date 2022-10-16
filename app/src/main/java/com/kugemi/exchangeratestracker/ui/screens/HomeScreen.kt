package com.kugemi.exchangeratestracker.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kugemi.exchangeratestracker.ui.components.RateItem
import com.kugemi.exchangeratestracker.viewmodels.ExchangeRatesViewModel
import com.kugemi.exchangeratestracker.viewmodels.FavoriteRatesViewModel

@Composable
fun HomeScreen(
    exchangeViewModel: ExchangeRatesViewModel,
    favoriteRatesViewModel: FavoriteRatesViewModel = viewModel()
) {

    val rates = exchangeViewModel.rates.observeAsState()

    rates.value.let { rateItems ->
        rateItems?.let { list ->
            LazyColumn() {
                items(list) { item ->
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