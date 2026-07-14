package com.mohdismail.currencyconverter.network

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import com.mohdismail.currencyconverter.Constants
import com.mohdismail.currencyconverter.data.model.CurrencyResponse
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService {

    suspend fun convertCurrency(
        from: String,
        to: String,
        amount: String
    ): CurrencyResponse {

        return HttpClientFactory.client
            .get(
                "${Constants.BASE_URL}${Constants.API_KEY}/pair/$from/$to/$amount"
            )
            .body()

    }

    suspend fun getCurrencies(): List<com.mohdismail.currencyconverter.data.model.CurrencyItem> {

        val response: JsonObject =
            HttpClientFactory.client
                .get("https://api.frankfurter.app/currencies")
                .body()

        return response.map {

            com.mohdismail.currencyconverter.data.model.CurrencyItem(

                code = it.key,

                name = it.value.jsonPrimitive.content

            )

        }.sortedBy { it.code }

    }

}