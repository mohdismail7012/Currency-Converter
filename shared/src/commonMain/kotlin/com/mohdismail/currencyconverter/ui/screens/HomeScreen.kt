package com.mohdismail.currencyconverter.ui.screens


import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import com.mohdismail.currencyconverter.ui.components.FavoriteCard
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohdismail.currencyconverter.ui.components.ConvertButton
import com.mohdismail.currencyconverter.ui.components.CurrencyPickerDialog
import com.mohdismail.currencyconverter.ui.components.ResultCard
import com.mohdismail.currencyconverter.ui.components.SwapButton
import com.mohdismail.currencyconverter.viewmodel.CurrencyViewModel
import androidx.compose.material3.Button

import com.mohdismail.currencyconverter.ui.components.HistoryCard
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.mohdismail.currencyconverter.utils.FlagUtils
import androidx.compose.material3.OutlinedTextFieldDefaults
import com.mohdismail.currencyconverter.theme.ThemeManager
import androidx.compose.runtime.LaunchedEffect

@Composable
fun HomeScreen(

) {

    var amount by remember {
        mutableStateOf("")
    }

    var fromCurrency by remember {
        mutableStateOf("- SELECT -")
    }

    var toCurrency by remember {
        mutableStateOf("- SELECT -")
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    var selectingFromCurrency by remember {
        mutableStateOf(true)
    }

    val viewModel = remember {
        CurrencyViewModel()
    }
    val result by viewModel.result.collectAsState()

    val favorites by viewModel.favorites.collectAsState()
    val history by viewModel.history.collectAsState()
    val currencies by viewModel.currencies.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCurrencies()
    }

    Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = 20.dp,
                    vertical = 24.dp
                ),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Currency Converter",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Convert currencies instantly",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        ThemeManager.toggleTheme()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {

                    Text(
                        if (ThemeManager.isDarkTheme)
                            "☀️ Light Mode"
                        else
                            "🌙 Dark Mode"
                    )

                }


            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Amount",
                style = MaterialTheme.typography.titleMedium
            )

            OutlinedTextField(
                value = amount,
                onValueChange = {
                    amount = it
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),

                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface
                ),

                colors = OutlinedTextFieldDefaults.colors(

                    focusedTextColor = MaterialTheme.colorScheme.onSurface,

                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,

                    focusedContainerColor = MaterialTheme.colorScheme.surface,

                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,

                    cursorColor = MaterialTheme.colorScheme.primary,

                    focusedBorderColor = MaterialTheme.colorScheme.primary,

                    unfocusedBorderColor = MaterialTheme.colorScheme.outline

                )
            )

            Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "From Currency",
            color = MaterialTheme.colorScheme.onBackground
        )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectingFromCurrency = true
                        showDialog = true
                    }
            ) {

                OutlinedTextField(
                    value = "${FlagUtils.getFlag(fromCurrency)} $fromCurrency",
                    onValueChange = {},
                    readOnly = true,
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),

                    textStyle = MaterialTheme.typography.bodyLarge,

                    colors = OutlinedTextFieldDefaults.colors(

                        disabledTextColor = MaterialTheme.colorScheme.onSurface,

                        disabledBorderColor = MaterialTheme.colorScheme.outline,

                        disabledContainerColor = MaterialTheme.colorScheme.surface

                    )
                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            SwapButton(
                onSwap = {

                    val temp = fromCurrency
                    fromCurrency = toCurrency
                    toCurrency = temp

                }
            )

            Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "To Currency",
            color = MaterialTheme.colorScheme.onBackground
        )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectingFromCurrency = false
                        showDialog = true
                    }
            ) {

                OutlinedTextField(
                    value = "${FlagUtils.getFlag(toCurrency)} $toCurrency",
                    onValueChange = {},
                    readOnly = true,
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),

                    textStyle = MaterialTheme.typography.bodyLarge,

                    colors = OutlinedTextFieldDefaults.colors(

                        disabledTextColor = MaterialTheme.colorScheme.onSurface,

                        disabledBorderColor = MaterialTheme.colorScheme.outline,

                        disabledContainerColor = MaterialTheme.colorScheme.surface

                    )
                )

            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {

                    viewModel.addFavorite(
                        fromCurrency = fromCurrency,
                        toCurrency = toCurrency
                    )

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {

                Text("⭐ Add to Favorites")

            }

        ConvertButton(

            onConvert = {

                if (
                    fromCurrency == "- SELECT -" ||
                    toCurrency == "- SELECT -"
                ) {
                    return@ConvertButton
                }

                viewModel.convert(
                    amount = amount,
                    fromCurrency = fromCurrency,
                    toCurrency = toCurrency
                )

            }

        )

            Spacer(modifier = Modifier.height(24.dp))

            ResultCard(
                result = result
            )
            Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Favorite Currency Pairs",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

            Spacer(modifier = Modifier.height(10.dp))

            favorites.forEach { favorite ->

                FavoriteCard(

                    favorite = favorite,

                    onClick = {

                        fromCurrency = favorite.fromCurrency
                        toCurrency = favorite.toCurrency

                    },

                    onDelete = {
                        viewModel.removeFavorite(favorite)
                    }
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Recent Conversions",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

            Spacer(modifier = Modifier.height(10.dp))

            history.forEach { item ->

                HistoryCard(

                    history = item,

                    onClick = {

                        amount = item.amount

                        fromCurrency = item.fromCurrency

                        toCurrency = item.toCurrency

                    },

                    onDelete = {

                        viewModel.removeHistory(item)

                    }

                )

            }

            }



        CurrencyPickerDialog(

            showDialog = showDialog,

            currencies = currencies,

            onDismiss = {

                showDialog = false

            },

            onCurrencySelected = { currency ->

                if (selectingFromCurrency) {

                    fromCurrency = currency

                } else {

                    toCurrency = currency

                }

            }

        )

    }
