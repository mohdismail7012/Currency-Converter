package com.mohdismail.currencyconverter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mohdismail.currencyconverter.theme.CurrencyConverterTheme
import com.mohdismail.currencyconverter.theme.ThemeManager
import com.mohdismail.currencyconverter.ui.screens.HomeScreen

@Composable
fun App() {
    CurrencyConverterTheme(
        darkTheme = ThemeManager.isDarkTheme
    ) {
        HomeScreen()
    }
}