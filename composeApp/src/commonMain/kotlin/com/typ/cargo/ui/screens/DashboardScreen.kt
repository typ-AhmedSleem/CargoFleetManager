package com.typ.cargo.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.typ.cargo.ui.converters.UiAngleConverter
import com.typ.cargo.ui.converters.UiBoardTemperatureConverter
import com.typ.cargo.ui.converters.UiDrivingModeConverter
import com.typ.cargo.ui.converters.UiPercentageConverter
import com.typ.cargo.ui.converters.UiSpeedConverter
import com.typ.cargo.viewmodels.DashboardViewModel
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pro.respawn.kmmutils.compose.slideInVertically
import pro.respawn.kmmutils.compose.slideOutVertically

object DashboardScreen : Screen, KoinComponent {
    @Composable
    override fun Content() {
        val viewModel: DashboardViewModel = remember { get() }
        val data = viewModel.dashboardData.value

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CupertinoText(
                text = "Dashboard",
                fontSize = 50.sp,
                style = CupertinoTheme.typography.largeTitle,
                modifier = Modifier.padding(bottom = 36.dp),
            )

            data?.let {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
                ) {
                    DashboardCard(
                        title = "Left Motor Speed",
                        modifier = Modifier.weight(1f),
                        value = UiSpeedConverter.convert(it.leftMotorSpeed)
                    )
                    DashboardCard(
                        title = "Right Motor Speed",
                        modifier = Modifier.weight(1f),
                        value = UiSpeedConverter.convert(it.rightMotorSpeed)
                    )
                }
                DashboardCard(
                    title = "Battery",
                    value = UiPercentageConverter.convert(it.batteryPercentage)
                )
                DashboardCard(
                    title = "Drivers Temperature",
                    value = UiBoardTemperatureConverter.convert(it.driversTemperature)
                )
                DashboardCard(
                    title = "Mode",
                    value = UiDrivingModeConverter.convert(it.drivingMode)
                )
                DashboardCard(
                    title = "Heading",
                    value = UiAngleConverter.convert(it.robotHeading)
                )
                DashboardCard(
                    title = "Errors",
                    value = it.errorLogs ?: "No Errors"
                )
            }
        }
    }

    @Composable
    private fun DashboardCard(
        modifier: Modifier = Modifier.fillMaxWidth(),
        title: String,
        value: String,
    ) {
        Box(
            modifier = modifier
                .border(
                    2.dp,
                    CupertinoTheme.colorScheme.secondarySystemBackground,
                    shape = RoundedCornerShape(16.dp)
                )
                .clip(RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column {
                CupertinoText(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = CupertinoTheme.typography.body
                )
                Spacer(Modifier.height(8.dp))
                AnimatedContent(
                    targetState = value,
                    transitionSpec = {
                        (slideInVertically()+ fadeIn()) togetherWith (slideOutVertically()+ fadeOut())
                    }
                ) {
                    CupertinoText(
                        text = value,
                        fontSize = 24.sp,
                        overflow = TextOverflow.Ellipsis,
                        style = CupertinoTheme.typography.title2.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }
}


