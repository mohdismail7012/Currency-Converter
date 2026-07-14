package com.mohdismail.currencyconverter.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SwapButton(
    onSwap: () -> Unit
) {

    Button(
        onClick = {
            onSwap()
        },
        modifier = Modifier.padding(vertical = 12.dp)
    ) {

        Text("⇅ Swap")

    }

}