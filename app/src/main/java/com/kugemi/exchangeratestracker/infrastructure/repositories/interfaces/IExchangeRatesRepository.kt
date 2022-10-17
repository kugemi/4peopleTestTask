package com.kugemi.exchangeratestracker.infrastructure.repositories.interfaces

import com.kugemi.exchangeratestracker.model.server_dto.ExchangeRates

interface IExchangeRatesRepository {
    suspend fun getRates(base: String): ExchangeRates
}