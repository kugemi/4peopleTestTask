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
import androidx.compose.ui.unit.dp
import com.kugemi.exchangeratestracker.data.resources.bottomBarHeight
import com.kugemi.exchangeratestracker.ui.components.RateItem
import com.kugemi.exchangeratestracker.utils.extensions.sortedBySortType
import com.kugemi.exchangeratestracker.viewmodels.ExchangeRatesViewModel
import com.kugemi.exchangeratestracker.viewmodels.FavoriteRatesViewModel

@Composable
fun HomeScreen(
    exchangeViewModel: ExchangeRatesViewModel,
    favoriteRatesViewModel: FavoriteRatesViewModel
) {

    val currentRate = exchangeViewModel.currentRate.observeAsState()

    currentRate.value?.let { rate ->
        exchangeViewModel.updateRates(rate)
    }

    val rates = exchangeViewModel.rates.observeAsState()

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
            isRatesEmpty = false
            LazyColumn(modifier = Modifier.padding(bottom = bottomBarHeight)) {
                items(list.sortedBySortType(sortType)) { item ->
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

//val rates = exchangeViewModel.getRates(rate)
//
//val sortType = exchangeViewModel.sortType.observeAsState()
//
//var isRatesEmpty by remember { mutableStateOf(rates.value.isNullOrEmpty()) }
//
//if (isRatesEmpty) {
//    Box(modifier = Modifier.fillMaxSize()) {
//        CircularProgressIndicator(
//            modifier = Modifier.align(Alignment.Center)
//        )
//    }
//}
//
//rates.value.let { rateItems ->
//    rateItems?.let { list ->
//        sortType.value?.let { sortType ->
//            isRatesEmpty = false
//            LazyColumn(
//                modifier = Modifier.padding(bottom = 56.dp)
//            ) {
//                items(list.sortedBySortType(sortType)) { item ->
//                    RateItem(
//                        name = item.name,
//                        value = item.value,
//                        favoriteRatesViewModel
//                    )
//                }
//            }
//        }
//    }
//}