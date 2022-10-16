package com.kugemi.exchangeratestracker.model.server_dto

import com.google.gson.annotations.SerializedName

data class ExchangeRates(
    @SerializedName("rates") val rates: List<RateItem>,
)