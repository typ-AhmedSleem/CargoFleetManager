package com.typ.cargo.ui.screens.dashboard.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.typ.cargo.ui.converters.UiSpeedConverter
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme

@Composable
internal fun MotorSpeedCard(
    modifier: Modifier = Modifier.fillMaxWidth(),
    title: String,
    motorSpeed: Int
) {
    DashboardCard(
        title = title,
        modifier = modifier,
        valueTextColor = when {
            motorSpeed == 0 -> CupertinoTheme.colorScheme.label
            else -> CupertinoTheme.colorScheme.accent
        },
        value = UiSpeedConverter.convert(motorSpeed)
    )
}