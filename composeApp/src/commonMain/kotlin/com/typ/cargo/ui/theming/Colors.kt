package com.typ.cargo.ui.theming

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme

object Colors {
    val success: Color = Color(0xFF4CAF50)
    val warning: Color = Color(0xFFFFC107)
    val error: Color = Color(0xFFF44336)

    // * Theme related colors * //
    val label: Color
        @Composable
        get() = CupertinoTheme.colorScheme.label

    val accent: Color
        @Composable
        get() = CupertinoTheme.colorScheme.accent

    val secondarySystemBackground: Color
        @Composable
        get() = CupertinoTheme.colorScheme.secondarySystemBackground

    val secondaryLabel: Color
        @Composable
        get() = CupertinoTheme.colorScheme.secondaryLabel
}