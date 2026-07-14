package com.mohdismail.currencyconverter.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohdismail.currencyconverter.model.ConversionHistory
import com.mohdismail.currencyconverter.utils.FlagUtils

@Composable
fun HistoryCard(

    history: ConversionHistory,

    onClick: () -> Unit,

    onDelete: () -> Unit

) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable {

                onClick()

            }

    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            verticalAlignment = Alignment.CenterVertically

        ) {

            Column(

                modifier = Modifier.weight(1f)

            ) {

                Text(

                    text = "${history.amount} ${FlagUtils.getFlag(history.fromCurrency)} ${history.fromCurrency}  →  ${FlagUtils.getFlag(history.toCurrency)} ${history.toCurrency}",

                    style = MaterialTheme.typography.titleMedium

                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(

                    text = "Result : ${history.result}"

                )

            }

            IconButton(

                onClick = {

                    onDelete()

                }

            ) {

                Icon(

                    imageVector = Icons.Default.Delete,

                    contentDescription = "Delete"

                )

            }

        }

    }

}