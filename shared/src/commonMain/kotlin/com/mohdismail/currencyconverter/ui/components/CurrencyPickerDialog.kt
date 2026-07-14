package com.mohdismail.currencyconverter.ui.components

import androidx.compose.material3.OutlinedTextFieldDefaults
import com.mohdismail.currencyconverter.utils.FlagUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.unit.dp
import com.mohdismail.currencyconverter.data.model.CurrencyItem

@Composable
fun CurrencyPickerDialog(

    showDialog: Boolean,

    currencies: List<CurrencyItem>,

    onDismiss: () -> Unit,

    onCurrencySelected: (String) -> Unit

) {

    if (!showDialog) return

    var searchText by remember {

        mutableStateOf("")

    }

    val filteredCurrencies = remember(searchText, currencies) {

        currencies.filter {

            it.code.contains(searchText, ignoreCase = true) ||
                    it.name.contains(searchText, ignoreCase = true)

        }

    }

    Dialog(

        onDismissRequest = {

            onDismiss()

        }

    ) {

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .background(
                    MaterialTheme.colorScheme.surface,
                    RoundedCornerShape(20.dp)
                )
                .padding(16.dp)

        ) {

            Text(
                text = "Select Currency",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(

                value = searchText,

                onValueChange = {

                    searchText = it

                },

                modifier = Modifier.fillMaxWidth(),

                label = {

                    Text("Search Currency")

                },

                colors = OutlinedTextFieldDefaults.colors(

                    focusedTextColor = MaterialTheme.colorScheme.onSurface,

                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,

                    focusedLabelColor = MaterialTheme.colorScheme.onSurface,

                    unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)

                )

            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(

                modifier = Modifier.weight(1f)

            ) {

                items(filteredCurrencies) { currency ->

                    Column(

                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                                onCurrencySelected(currency.code)

                                onDismiss()

                            }
                            .padding(16.dp)

                    ) {

                        Text(
                            text = "${FlagUtils.getFlag(currency.code)} ${currency.code}",
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = currency.name,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                    }

                    HorizontalDivider()

                }

            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.End,

                verticalAlignment = Alignment.CenterVertically

            ) {

                TextButton(

                    onClick = {

                        onDismiss()

                    }

                ) {

                    Text("Close")

                }

            }

        }

    }

}