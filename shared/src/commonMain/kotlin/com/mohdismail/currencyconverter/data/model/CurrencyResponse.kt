package com.mohdismail.currencyconverter.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyResponse(

    @SerialName("conversion_result")
    val conversionResult: Double
)