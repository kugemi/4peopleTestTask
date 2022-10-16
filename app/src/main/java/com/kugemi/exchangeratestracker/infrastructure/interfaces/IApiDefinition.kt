package com.kugemi.exchangeratestracker.infrastructure.interfaces

import com.kugemi.exchangeratestracker.model.server_dto.ExchangeRates
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface IApiDefinition {

    @GET("latest?apikey=FKuXZlod30zT5WstSUzSVBKMKnVHEZOj")
    fun getRates(): Deferred<ExchangeRates>
}