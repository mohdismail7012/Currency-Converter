package com.mohdismail.currencyconverter.viewmodel

import com.mohdismail.currencyconverter.data.model.CurrencyItem
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohdismail.currencyconverter.model.FavoriteCurrency
import com.mohdismail.currencyconverter.repository.CurrencyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.mohdismail.currencyconverter.model.ConversionHistory



class CurrencyViewModel : ViewModel() {

    private val repository = CurrencyRepository()

    // ---------------- Favorites ----------------

    private val _favorites = MutableStateFlow<List<FavoriteCurrency>>(emptyList())

    private val _currencies =
        MutableStateFlow<List<CurrencyItem>>(emptyList())

    val currencies: StateFlow<List<CurrencyItem>> =
        _currencies.asStateFlow()

    private val _history = MutableStateFlow<List<ConversionHistory>>(emptyList())

    val history: StateFlow<List<ConversionHistory>> =
        _history.asStateFlow()

    val favorites: StateFlow<List<FavoriteCurrency>> =
        _favorites.asStateFlow()

    // ---------------- Result ----------------

    private val _result = MutableStateFlow("0.00")

    val result: StateFlow<String> = _result.asStateFlow()

    // ---------------- Convert Currency ----------------

    fun convert(
        amount: String,
        fromCurrency: String,
        toCurrency: String
    ) {

        if (amount.isBlank()) {
            _result.value = "Please enter an amount."
            return
        }

        if (amount.toDoubleOrNull() == null) {
            _result.value = "Invalid input. Please enter numbers only."
            return
        }

        viewModelScope.launch {

            try {

                val response = repository.convertCurrency(
                    from = fromCurrency,
                    to = toCurrency,
                    amount = amount
                )

                val convertedResult = response.conversionResult.toString()

                _result.value = convertedResult

                val historyList = _history.value.toMutableList()

                historyList.add(

                    0,

                    ConversionHistory(

                        amount = amount,

                        fromCurrency = fromCurrency,

                        toCurrency = toCurrency,

                        result = convertedResult

                    )

                )

                _history.value = historyList.take(10)

            } catch (e: Exception) {

                e.printStackTrace()

                println("ERROR = ${e.message}")

                _result.value = e.message ?: "Unknown Error"

            }

        }

    }

    // ---------------- Add Favorite ----------------

    fun addFavorite(
        fromCurrency: String,
        toCurrency: String
    ) {

        val currentList = _favorites.value.toMutableList()

        val favorite = FavoriteCurrency(
            fromCurrency = fromCurrency,
            toCurrency = toCurrency
        )

        if (!currentList.contains(favorite)) {

            currentList.add(favorite)

            _favorites.value = currentList

        }

    }

    // ---------------- Remove Favorite ----------------

    fun removeFavorite(
        favorite: FavoriteCurrency
    ) {

        val currentList = _favorites.value.toMutableList()

        currentList.remove(favorite)

        _favorites.value = currentList

    }
    // ---------------- Remove History ----------------

    fun removeHistory(
        history: ConversionHistory
    ) {

        val currentList = _history.value.toMutableList()

        currentList.remove(history)

        _history.value = currentList

    }
    fun loadCurrencies() {

        viewModelScope.launch {

            try {

                val list = repository.getCurrencies()

                println("Currencies Loaded = ${list.size}")

                _currencies.value = list

            } catch (e: Exception) {

                e.printStackTrace()

                _result.value = "Currency Error:\n${e.message}"

                println("====================================")
                println("Currency API Error")
                println("Message = ${e.message}")
                println("Exception = ${e}")
                println("Class = ${e::class.simpleName}")
                println("====================================")

            }

        }

    }

}