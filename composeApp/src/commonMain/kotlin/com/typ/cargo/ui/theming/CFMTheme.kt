package com.typ.cargo.ui.theming

import androidx.compose.runtime.Composable
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme
import io.github.alexzhirkevich.cupertino.theme.lightColorScheme

@Composable
fun CFMTheme(content: @Composable () -> Unit) {
    CupertinoTheme(
        colorScheme = lightColorScheme(),
        typography = CupertinoTheme.typography,
        shapes = CupertinoTheme.shapes,
        content = content
    )
}