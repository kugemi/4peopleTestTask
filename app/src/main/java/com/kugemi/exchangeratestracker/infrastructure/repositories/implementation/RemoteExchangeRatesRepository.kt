package com.kugemi.exchangeratestracker.infrastructure.repositories.implementation

import com.kugemi.exchangeratestracker.infrastructure.interfaces.IServerClient
import com.kugemi.exchangeratestracker.infrastructure.repositories.interfaces.IExchangeRatesRepository
import com.kugemi.exchangeratestracker.model.server_dto.ExchangeRates

class RemoteExchangeRatesRepository(private val serverClient: IServerClient) : IExchangeRatesRepository {
    override suspend fun getRates(): ExchangeRates {
        return serverClient.getRates()
    }
}