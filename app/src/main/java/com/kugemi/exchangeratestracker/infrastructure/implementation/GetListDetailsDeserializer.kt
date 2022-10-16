package com.kugemi.exchangeratestracker.infrastructure.implementation

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.kugemi.exchangeratestracker.model.server_dto.RateItem
import java.lang.reflect.Type

class GetListDetailsDeserializer : JsonDeserializer<List<RateItem>> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): List<RateItem> {
        val rateItems = mutableListOf<RateItem>()

        val jsonObject = json?.asJsonObject

        jsonObject?.let {
            val entries = jsonObject.entrySet()
            for (entry in entries) {
                val value = entry.value.asString
                rateItems.add(RateItem(entry.key, value))
            }
        }

        return rateItems
    }
}