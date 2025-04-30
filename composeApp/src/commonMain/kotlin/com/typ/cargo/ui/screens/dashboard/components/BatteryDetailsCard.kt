package com.typ.cargo.ui.screens.dashboard.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.typ.cargo.ui.converters.UiBatteryInfoConverter
import com.typ.cargo.ui.theming.Colors

@Composable
internal fun BatteryDetailsCard(
    modifier: Modifier = Modifier.fillMaxWidth(),
    carBatteryPercentage: Int,
) {
    /*
        Value text will be styled as following:
            1. Battery percentage has a color according to its value in range 0 to 100
            2. Battery percentage is a percentage
            3. Battery percentage is bold
        */
    DashboardCard(
        modifier = modifier,
        title = "Battery Info",
        valueTextColor = when (carBatteryPercentage) {
            in 0..30 -> Colors.error
            in 31..60 -> Colors.warning
            else -> Colors.success
        },
        value = UiBatteryInfoConverter.convert(carBatteryPercentage)
    )
}