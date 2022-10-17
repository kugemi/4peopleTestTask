package com.kugemi.exchangeratestracker.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.kugemi.exchangeratestracker.data.enums.SortType
import com.kugemi.exchangeratestracker.model.local_dto.FavoriteRate
import com.kugemi.exchangeratestracker.viewmodels.ExchangeRatesViewModel

@Composable
fun SortTypePicker(
    exchangeViewModel: ExchangeRatesViewModel
) {
    val currentSortType = exchangeViewModel.sortType.observeAsState()

    var isShowPopup by remember { mutableStateOf(false) }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
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
            modifier = Modifier.padding(15.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            currentSortType.value?.let {
                Text(
                    text = getSortTypeName(it),
                    color = Color.Black,
                    fontSize = 16.sp
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
    Popup(
        alignment = Alignment.TopCenter,
        properties = PopupProperties()
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE0EAFC)
            ),
        ) {
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                item {
                    Text(
                        modifier = Modifier.clickable {
                            exchangeViewModel.setSortType(SortType.ALPHABET_ASCENDING)
                            updateIsShowPopup(false)
                        },
                        text = getSortTypeName(SortType.ALPHABET_ASCENDING),
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
                item {
                    Divider(color = Color.Black)
                }
                item {
                    Text(
                        modifier = Modifier.clickable {
                            exchangeViewModel.setSortType(SortType.ALPHABET_DESCENDING)
                            updateIsShowPopup(false)
                        },
                        text = getSortTypeName(SortType.ALPHABET_DESCENDING),
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
                item {
                    Divider(color = Color.Black)
                }
                item {
                    Text(
                        modifier = Modifier.clickable {
                            exchangeViewModel.setSortType(SortType.VALUE_ASCENDING)
                            updateIsShowPopup(false)
                        },
                        text = getSortTypeName(SortType.VALUE_ASCENDING),
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
                item {
                    Divider(color = Color.Black)
                }
                item {
                    Text(
                        modifier = Modifier.clickable {
                            exchangeViewModel.setSortType(SortType.VALUE_DESCENDING)
                            updateIsShowPopup(false)
                        },
                        text = getSortTypeName(SortType.VALUE_DESCENDING),
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

private fun getSortTypeName(type: SortType): String {
    return when(type) {
        SortType.ALPHABET_ASCENDING -> "Name ascending"
        SortType.ALPHABET_DESCENDING -> "Name descending"
        SortType.VALUE_ASCENDING -> "Value ascending"
        SortType.VALUE_DESCENDING -> "Value descending"
    }
}