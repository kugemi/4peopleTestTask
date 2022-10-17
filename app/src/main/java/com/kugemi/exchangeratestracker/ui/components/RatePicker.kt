package com.kugemi.exchangeratestracker.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.kugemi.exchangeratestracker.data.enums.SortType
import com.kugemi.exchangeratestracker.data.resources.backgroundTopColor
import com.kugemi.exchangeratestracker.data.resources.defaultPadding
import com.kugemi.exchangeratestracker.data.resources.fontSizeSmall
import com.kugemi.exchangeratestracker.viewmodels.ExchangeRatesViewModel

@Composable
fun RatePicker(
    exchangeViewModel: ExchangeRatesViewModel
) {
    val currentRate = exchangeViewModel.currentRate.observeAsState()

    var isShowPopup by remember { mutableStateOf(false) }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(defaultPadding)
            .clickable { isShowPopup = true },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        if (isShowPopup) {
            PopupPicker(
                exchangeViewModel = exchangeViewModel,
                updateIsShowPopup = {
                    isShowPopup = it
                }
            )
        }
        Row(
            modifier = Modifier.padding(defaultPadding),
            horizontalArrangement = Arrangement.Center
        ) {
            currentRate.value?.let { currentRate ->
                Text(
                    text = currentRate,
                    color = Color.Black,
                    fontSize = fontSizeSmall
                )
            }
        }
    }
}

@Composable
private fun PopupPicker(
    exchangeViewModel: ExchangeRatesViewModel,
    updateIsShowPopup: (Boolean) -> Unit
) {
    val rates = exchangeViewModel.rates.observeAsState()

    Popup(
        alignment = Alignment.TopCenter,
        properties = PopupProperties()
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(defaultPadding),
            colors = CardDefaults.cardColors(
                containerColor = backgroundTopColor
            ),
        ) {
            rates.value?.let { rates ->
                LazyColumn(modifier = Modifier.padding(defaultPadding)) {
                    itemsIndexed(rates) { index, item ->
                        Text(
                            modifier = Modifier
                                .padding(defaultPadding)
                                .clickable {
                                    exchangeViewModel.setCurrentRate(item.name)
                                    updateIsShowPopup(false)
                            },
                            text = item.name,
                            color = Color.Black,
                            fontSize = fontSizeSmall
                        )
                        if (index < rates.lastIndex) {
                            Divider(color = Color.Black)
                        }
                    }
                }
            }
        }
    }
}