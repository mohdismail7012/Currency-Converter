package com.mohdismail.currencyconverter.utils

object FlagUtils {

    private val flags = mapOf(

        // Major Currencies
        "USD" to "🇺🇸",
        "EUR" to "🇪🇺",
        "GBP" to "🇬🇧",
        "JPY" to "🇯🇵",
        "INR" to "🇮🇳",
        "AUD" to "🇦🇺",
        "CAD" to "🇨🇦",
        "CHF" to "🇨🇭",
        "CNY" to "🇨🇳",
        "AED" to "🇦🇪",

        // Europe
        "BGN" to "🇧🇬",
        "CZK" to "🇨🇿",
        "DKK" to "🇩🇰",
        "HUF" to "🇭🇺",
        "ISK" to "🇮🇸",
        "NOK" to "🇳🇴",
        "PLN" to "🇵🇱",
        "RON" to "🇷🇴",
        "SEK" to "🇸🇪",

        // Asia
        "HKD" to "🇭🇰",
        "IDR" to "🇮🇩",
        "ILS" to "🇮🇱",
        "KRW" to "🇰🇷",
        "MYR" to "🇲🇾",
        "PHP" to "🇵🇭",
        "SGD" to "🇸🇬",
        "THB" to "🇹🇭",
        "TRY" to "🇹🇷",

        // Americas
        "BRL" to "🇧🇷",
        "MXN" to "🇲🇽",
        "NZD" to "🇳🇿",

        // Africa
        "ZAR" to "🇿🇦",

        // South Asia
        "PKR" to "🇵🇰",
        "BDT" to "🇧🇩",
        "LKR" to "🇱🇰",

        // Middle East
        "SAR" to "🇸🇦"

    )

    fun getFlag(currencyCode: String): String {
        return flags[currencyCode.uppercase()] ?: "🏳️"
    }
}