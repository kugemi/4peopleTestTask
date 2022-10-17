package com.kugemi.exchangeratestracker.infrastructure.implementation

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kugemi.exchangeratestracker.infrastructure.interfaces.IApiDefinition
import com.kugemi.exchangeratestracker.infrastructure.interfaces.IServerClient
import com.kugemi.exchangeratestracker.model.server_dto.ExchangeRates
import com.kugemi.exchangeratestracker.model.server_dto.RateItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class ServerClient : IServerClient {

    private val service: IApiDefinition

    init {
        val gsonBuilder = GsonBuilder()
        val listType: Type = object : TypeToken<MutableList<RateItem>>() {}.type
        gsonBuilder.registerTypeAdapter(listType, GetListDetailsDeserializer())
        val gson = gsonBuilder.create()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .build()
        service = retrofit.create(IApiDefinition::class.java)
    }

    override suspend fun getRates(base: String): ExchangeRates {
        val serverResult: ExchangeRates = service.getRates(APIKEY, base).await()
        return ExchangeRates(serverResult.rates)
    }

    companion object {
        private const val BASE_URL = "https://api.apilayer.com/exchangerates_data/"
        private const val APIKEY = "h81DOL3ioFd04egOOHe0B7Q2tkPoo03l"
    }
}