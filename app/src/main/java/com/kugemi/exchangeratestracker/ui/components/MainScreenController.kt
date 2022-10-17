package com.kugemi.exchangeratestracker.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kugemi.exchangeratestracker.viewmodels.ExchangeRatesViewModel

@Composable
fun MainScreenController(
    exchangeViewModel: ExchangeRatesViewModel
) {
    Row {
        Box(modifier = Modifier.weight(1f)) {
            RatePicker(exchangeViewModel = exchangeViewModel)
        }
        Box(modifier = Modifier.weight(1f)) {
            SortTypePicker(exchangeViewModel = exchangeViewModel)
        }
    }
}