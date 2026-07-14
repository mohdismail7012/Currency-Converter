package com.mohdismail.currencyconverter.repository

import com.mohdismail.currencyconverter.data.model.CurrencyResponse
import com.mohdismail.currencyconverter.network.ApiService

class CurrencyRepository {

    private val apiService = ApiService()

    suspend fun convertCurrency(
        from: String,
        to: String,
        amount: String
    ): CurrencyResponse {

        return apiService.convertCurrency(
            from = from,
            to = to,
            amount = amount
        )

    }
    suspend fun getCurrencies() =
        apiService.getCurrencies()

}