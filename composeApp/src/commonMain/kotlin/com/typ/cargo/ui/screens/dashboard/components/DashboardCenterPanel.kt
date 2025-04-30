package com.typ.cargo.ui.screens.dashboard.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.typ.cargo.data.models.DashboardData
import com.typ.cargo.ui.theming.Colors
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme

@Composable
internal fun RowScope.DashboardCenterPanel(
    // todo: Should add the live stream feed here
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
        PanelTitleText("Mission Tracker")

        dashboardData?.let {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    /*.border(
                        width = 2.dp,
                        shape = RoundedCornerShape(16.dp),
                        color = CupertinoTheme.colorScheme.secondarySystemBackground,
                    )*/
                    .clip(RoundedCornerShape(16.dp))
                    .background(CupertinoTheme.colorScheme.secondarySystemBackground)
                    .heightIn(min = 500.dp),
                contentAlignment = Alignment.Center
            ) {
                PanelTitleText("Live camera stream will be displayed here")
            }

            DashboardCard(
                title = "Connection Status",
                valueTextColor = Colors.error,
                value = "Not connected to Cargo AMR yet!"
            )

            DashboardCard(
                title = "Connection Quality",
                value = "Connection Quality indicator will be displayed here"
            )

            DashboardCard(
                title = "Mission Details",
                value = "Mission details will be displayed here"
            )

            DashboardCard(
                title = "Load Weight",
                value = "Load weight will be displayed here"
            )

        }
    }
}