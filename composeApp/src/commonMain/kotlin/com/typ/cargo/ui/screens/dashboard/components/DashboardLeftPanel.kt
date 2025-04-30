package com.typ.cargo.ui.screens.dashboard.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.typ.cargo.data.models.DashboardData
import com.typ.cargo.ui.converters.UiDrivingModeConverter
import com.typ.cargo.ui.theming.Colors
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme

@Composable
internal fun RowScope.DashboardLeftPanel(
    dashboardData: DashboardData?,
    weight: Float = 1f
) {
    Column(
        modifier = Modifier
            .weight(weight)
            .fillMaxHeight()
            .border(
                width = 2.dp,
                shape = RoundedCornerShape(16.dp),
                color = CupertinoTheme.colorScheme.secondarySystemBackground,
            )
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            )
            .verticalScroll(rememberScrollState())
            .animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PanelTitleText("Realtime Information")

        dashboardData?.let {
            BatteryDetailsCard(carBatteryPercentage = it.batteryPercentage)

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
            ) {
                MotorSpeedCard(
                    title = "Left Motor Speed",
                    modifier = Modifier.weight(1f),
                    motorSpeed = it.leftMotorSpeed
                )
                MotorSpeedCard(
                    title = "Right Motor Speed",
                    modifier = Modifier.weight(1f),
                    motorSpeed = it.rightMotorSpeed
                )
            }

            DriversTemperatureCard(temperature = it.driversTemperature)

            DashboardCard(
                title = "Driving Mode",
                valueTextColor = CupertinoTheme.colorScheme.accent,
                value = UiDrivingModeConverter.convert(it.drivingMode)
            )

            RobotHeadingCard(robotHeading = it.robotHeading)

            DashboardCard(
                title = "Errors",
                valueTextColor = when {
                    it.errorLogs.isNullOrEmpty() -> CupertinoTheme.colorScheme.label
                    else -> Colors.error
                },
                value = it.errorLogs ?: "No errors have been reported yet"
            )
        }
    }
}