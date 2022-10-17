package com.kugemi.exchangeratestracker.infrastructure.interfaces

import com.kugemi.exchangeratestracker.model.server_dto.ExchangeRates
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiDefinition {

    @GET("latest")
    fun getRates(@Query("apikey") apikey: String, @Query("base") base: String): Deferred<ExchangeRates>
}