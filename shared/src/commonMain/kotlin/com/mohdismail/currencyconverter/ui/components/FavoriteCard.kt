package com.mohdismail.currencyconverter.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohdismail.currencyconverter.model.FavoriteCurrency
import com.mohdismail.currencyconverter.utils.FlagUtils

@Composable
fun FavoriteCard(

    favorite: FavoriteCurrency,

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

            Icon(

                imageVector = Icons.Default.Star,

                contentDescription = null

            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(

                text = "${FlagUtils.getFlag(favorite.fromCurrency)} ${favorite.fromCurrency}  →  ${FlagUtils.getFlag(favorite.toCurrency)} ${favorite.toCurrency}",

                modifier = Modifier.weight(1f)

            )

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