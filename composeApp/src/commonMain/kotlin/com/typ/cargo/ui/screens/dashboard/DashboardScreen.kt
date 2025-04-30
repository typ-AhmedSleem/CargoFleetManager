package com.typ.cargo.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.typ.cargo.ui.converters.UiDrivingModeConverter
import com.typ.cargo.ui.screens.dashboard.components.BatteryDetailsCard
import com.typ.cargo.ui.screens.dashboard.components.DashboardCard
import com.typ.cargo.ui.screens.dashboard.components.DriversTemperatureCard
import com.typ.cargo.ui.screens.dashboard.components.MotorSpeedCard
import com.typ.cargo.ui.screens.dashboard.components.RobotHeadingCard
import com.typ.cargo.ui.theming.Colors
import com.typ.cargo.viewmodels.DashboardViewModel
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object DashboardScreen : Screen, KoinComponent {
    @Composable
    override fun Content() {
        val viewModel: DashboardViewModel = remember { get() }
        val data by viewModel.dashboardData

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CupertinoText(
                text = "Cargo Fleet Manager",
                fontSize = 50.sp,
                style = CupertinoTheme.typography.largeTitle,
                modifier = Modifier.padding(
                    vertical = 24.dp,
                    horizontal = 8.dp
                ),
            )

            data?.let {
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
}


