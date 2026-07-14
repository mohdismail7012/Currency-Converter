package com.mohdismail.currencyconverter.model

data class ConversionHistory(

    val amount: String,

    val fromCurrency: String,

    val toCurrency: String,

    val result: String

)