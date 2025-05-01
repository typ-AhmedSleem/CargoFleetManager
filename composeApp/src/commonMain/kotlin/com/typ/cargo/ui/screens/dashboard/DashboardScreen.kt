package com.typ.cargo.ui.screens.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.typ.cargo.ui.screens.dashboard.components.DashboardCenterPanel
import com.typ.cargo.ui.screens.dashboard.components.DashboardLeftPanel
import com.typ.cargo.ui.screens.dashboard.components.DashboardRightPanel
import com.typ.cargo.ui.screens.dashboard.components.ThreeEqualSectionsRow
import com.typ.cargo.viewmodels.DashboardViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object DashboardScreen : Screen, KoinComponent {
    @Composable
    override fun Content() {
        // * Runtime * //
        val viewModel: DashboardViewModel = remember { get() }
        val robotInfo by viewModel.robotInfo

        // * UI * //
        ThreeEqualSectionsRow(Modifier
            .fillMaxSize()
            .padding(
                vertical = 16.dp,
                horizontal = 16.dp
            )) {
            DashboardLeftPanel(robotInfo, 0.75f)
            DashboardCenterPanel(robotInfo, 1f)
            DashboardRightPanel(0.5f)
        }
    }
}


