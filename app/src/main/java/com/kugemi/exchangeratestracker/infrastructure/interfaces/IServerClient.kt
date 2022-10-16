package com.kugemi.exchangeratestracker.infrastructure.interfaces

import com.kugemi.exchangeratestracker.model.server_dto.ExchangeRates

interface IServerClient {

    suspend fun getRates(): ExchangeRates
}