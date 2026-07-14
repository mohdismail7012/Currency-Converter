package com.mohdismail.currencyconverter.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConvertButton(
    onConvert: () -> Unit
) {

    Button(
        onClick = {
            onConvert()
        },
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
    ) {

        Text("Convert")

    }

}