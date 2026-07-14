package com.mohdismail.currencyconverter

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mohdismail.currencyconverter.theme.CurrencyConverterTheme
import com.mohdismail.currencyconverter.theme.ThemeManager
import com.mohdismail.currencyconverter.ui.screens.HomeScreen

@Composable
@Preview
fun App() {

    CurrencyConverterTheme(
        darkTheme = ThemeManager.isDarkTheme
    ) {

        HomeScreen()

    }

}