package com.mohdismail.currencyconverter.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(

    primary = Primary,
    secondary = Secondary,

    background = Background,
    surface = Surface,

    onPrimary = Surface,
    onSecondary = Surface,
    onBackground = TextPrimary,
    onSurface = TextPrimary,

)

private val DarkColors = darkColorScheme(

    primary = DarkPrimary,
    secondary = DarkSecondary,

    background = DarkBackground,
    surface = DarkSurface,

    onPrimary = DarkText,
    onBackground = DarkText,
    onSurface = DarkText

)

@Composable
fun CurrencyConverterTheme(

    darkTheme: Boolean = isSystemInDarkTheme(),

    content: @Composable () -> Unit

) {

    MaterialTheme(

        colorScheme = if (darkTheme) DarkColors else LightColors,

        typography = Typography(),

        content = content

    )

}