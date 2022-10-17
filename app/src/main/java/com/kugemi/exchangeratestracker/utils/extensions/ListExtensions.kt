package com.kugemi.exchangeratestracker.utils.extensions

import com.kugemi.exchangeratestracker.data.enums.SortType
import com.kugemi.exchangeratestracker.model.server_dto.RateItem

fun List<RateItem>.sortedBySortType(type: SortType): List<RateItem> {
    return when (type) {
        SortType.ALPHABET_ASCENDING -> sortedBy { it.name }
        SortType.ALPHABET_DESCENDING -> sortedByDescending { it.name }
        SortType.VALUE_ASCENDING -> sortedBy { it.value }
        SortType.VALUE_DESCENDING -> sortedByDescending { it.value }
    }
}