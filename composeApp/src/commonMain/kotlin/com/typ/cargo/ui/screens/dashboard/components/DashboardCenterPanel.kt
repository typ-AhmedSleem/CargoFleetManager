package com.typ.cargo.ui.screens.dashboard.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.typ.cargo.data.models.StreamVideoFrame
import com.typ.cargo.data.models.StreamVideoFrame.*
import com.typ.cargo.ui.theming.Colors
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme
import kotlinx.coroutines.flow.Flow

@Composable
internal fun RowScope.DashboardCenterPanel(
    videoStream: Flow<StreamVideoFrame>,
    weight: Float = 1f
) {
    // * Runtime * //
    val videoFrame by videoStream.collectAsStateWithLifecycle(NoFrame)

    // * UI * //
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

        VideoStreamViewer(
            videoFrame = videoFrame,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 500.dp),
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
