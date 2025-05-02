package com.typ.cargo.ui.screens.dashboard.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.typ.cargo.ui.theming.Colors
import io.github.alexzhirkevich.cupertino.icons.CupertinoIcons
import io.github.alexzhirkevich.cupertino.icons.outlined.Battery100
import io.github.alexzhirkevich.cupertino.icons.outlined.Car
import io.github.alexzhirkevich.cupertino.icons.outlined.Exclamationmark
import io.github.alexzhirkevich.cupertino.icons.outlined.ExclamationmarkTriangle
import io.github.alexzhirkevich.cupertino.icons.outlined.HandRaised
import io.github.alexzhirkevich.cupertino.icons.outlined.Rotate3d
import io.github.alexzhirkevich.cupertino.icons.outlined.Speedometer
import io.github.alexzhirkevich.cupertino.icons.outlined.Wifi
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme

@Composable
internal fun RowScope.DashboardRightPanel(
    // todo: add the actual data model to be used in this panel
    weight: Float = 1f
) {
    Column(
        modifier = Modifier
            .weight(weight)
            .fillMaxHeight()
            .clip(RoundedCornerShape(16.dp))
            .background(CupertinoTheme.colorScheme.secondarySystemBackground.copy(0.75f))
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            )
            .verticalScroll(rememberScrollState())
            .animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        PanelTitleText("Command & Control")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
        ) {
            RobotControlActionButton(
                title = "Connect",
                modifier = Modifier.weight(1f),
                icon = CupertinoIcons.Default.Wifi,
            ) {
                println("Connect is clicked")
            }
            RobotControlActionButton(
                title = "Charge",
                modifier = Modifier.weight(1f),
                icon = CupertinoIcons.Default.Battery100,
            )
        }

        RobotControlActionButton(
            title = "Switch driving mode",
            modifier = Modifier.fillMaxWidth(),
            icon = CupertinoIcons.Default.Speedometer,
        )

        RobotControlActionButton(
            title = "Active Emergency Brakes",
            modifier = Modifier.fillMaxWidth(),
            icon = CupertinoIcons.Default.ExclamationmarkTriangle,
        )

        RobotControlActionButton(
            title = "Enable Pivot Steering",
            modifier = Modifier.fillMaxWidth(),
            icon = CupertinoIcons.Default.Rotate3d,
        )

        RobotControlActionButton(
            title = "Begin arm control",
            modifier = Modifier.fillMaxWidth(),
            icon = CupertinoIcons.Default.HandRaised,
        )

        DashboardCard(
            title = "Connection Status",
            valueTextColor = Colors.error,
            value = "Not connected to Cargo AMR yet!"
        )

        DashboardCard(
            title = "Connection Quality",
            value = "Connection Quality indicator will be displayed here"
        )
    }
}