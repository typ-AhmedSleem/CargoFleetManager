package com.typ.cargo.ui.screens.dashboard.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.typ.cargo.ui.converters.UiBatteryInfoConverter
import com.typ.cargo.ui.converters.UiBoardTemperatureConverter
import com.typ.cargo.ui.theming.Colors
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme

@Composable
internal fun DriversTemperatureCard(
    modifier: Modifier = Modifier.fillMaxWidth(),
    temperature: Int,
) {
    DashboardCard(
        modifier = modifier,
        title = "Drivers Temperature",
        valueTextColor = when {
            temperature in 28..35 -> Colors.success
            temperature in 36..40 -> Colors.warning
            temperature >= 41 -> Colors.error
            else -> CupertinoTheme.colorScheme.label
        },
        value = UiBoardTemperatureConverter.convert(temperature)
    )
}