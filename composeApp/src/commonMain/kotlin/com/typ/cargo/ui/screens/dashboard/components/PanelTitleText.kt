package com.typ.cargo.ui.screens.dashboard.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme

@Composable
internal fun PanelTitleText(
    text: String,
    modifier: Modifier = Modifier,
) {
    CupertinoText(
        text = text,
        fontSize = 24.sp,
        color = CupertinoTheme.colorScheme.label,
        style = CupertinoTheme.typography.largeTitle,
        modifier = modifier.padding(
            vertical = 16.dp,
            horizontal = 8.dp
        ),
    )
}