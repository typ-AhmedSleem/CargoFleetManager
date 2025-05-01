@file:OptIn(ExperimentalCupertinoApi::class)

package com.typ.cargo.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cargofleetmanager.composeapp.generated.resources.Res
import cargofleetmanager.composeapp.generated.resources.picAhmedSleem
import com.typ.cargo.ui.screens.dashboard.DashboardScreen
import com.typ.cargo.ui.theming.Colors
import io.github.alexzhirkevich.cupertino.CupertinoButton
import io.github.alexzhirkevich.cupertino.CupertinoButtonDefaults
import io.github.alexzhirkevich.cupertino.CupertinoIcon
import io.github.alexzhirkevich.cupertino.CupertinoIconButton
import io.github.alexzhirkevich.cupertino.CupertinoNavigateBackButton
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.ExperimentalCupertinoApi
import io.github.alexzhirkevich.cupertino.icons.CupertinoIcons
import io.github.alexzhirkevich.cupertino.icons.outlined.ChevronDown
import io.github.alexzhirkevich.cupertino.icons.outlined.Gear
import io.github.alexzhirkevich.cupertino.icons.outlined.Message
import io.github.alexzhirkevich.cupertino.icons.outlined.Moon
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme
import org.jetbrains.compose.resources.DrawableResource
import pro.respawn.kmmutils.compose.resources.painter

@Composable
fun CFMTopAppBar(
    navigator: Navigator?,
    currentScreen: Screen?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .padding(
                vertical = 16.dp,
                horizontal = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            alignment = Alignment.End,
            space = 8.dp,
        )
    ) {
        AnimatedVisibility(currentScreen !is DashboardScreen) {
            CupertinoNavigateBackButton(
                onClick = { navigator?.pop() }
            ) {
                CupertinoText("Back")
            }
        }
        Spacer(Modifier.width(8.dp))
        CupertinoText(
            maxLines = 1,
            text = "Cargo Fleet Manager",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f),
            style = CupertinoTheme.typography.title2,
        )

        Spacer(Modifier.width(16.dp))

        ConnectionIndicatorButton(
            connected = false,
            onClick = {}
        )

        TopBarActionButton(
            icon = CupertinoIcons.Default.Moon,
            onClick = {
            }
        )

        TopBarActionButton(
            icon = CupertinoIcons.Default.Message,
            onClick = {
            }
        )

        TopBarActionButton(
            icon = CupertinoIcons.Default.Gear,
            onClick = {
            }
        )

        UserProfileCard(
            username = "Ahmed Sleem\nAdministrator",
            picture = Res.drawable.picAhmedSleem,
            onClick = {
            }
        )
    }
}

@Composable
private fun TopBarActionButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    CupertinoIconButton(
        onClick = onClick,
        modifier = modifier
            .size(64.dp),
        border = BorderStroke(
            width = 2.dp,
            color = Colors.secondarySystemBackground
        ),
        colors = CupertinoButtonDefaults.plainButtonColors(
            contentColor = Colors.label
        )
    ) {
        CupertinoIcon(
            imageVector = icon,
            contentDescription = null
        )
    }
}

@Composable
private fun ConnectionIndicatorButton(
    modifier: Modifier = Modifier,
    connected: Boolean,
    onClick: () -> Unit,
) {
    CupertinoButton(
        onClick = onClick,
        modifier = modifier
            .height(80.dp)
            .widthIn(min = 156.dp),
        shape = RoundedCornerShape(50),
        colors = CupertinoButtonDefaults.filledButtonColors(
            containerColor = Colors.success,
            contentColor = Color.White
        )
    ) {
        CupertinoText("Connected")
    }
}

@Composable
fun UserProfileCard(
    modifier: Modifier = Modifier,
    username: String,
    picture: DrawableResource,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .border(
                width = 2.dp,
                shape = RoundedCornerShape(16.dp),
                color = CupertinoTheme.colorScheme.secondarySystemBackground,
            )
            .clickable(onClick = onClick)
            .padding(
                vertical = 8.dp,
                horizontal = 10.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        Image(
            contentScale = ContentScale.Crop,
            painter = picture.painter(),
            contentDescription = "",
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Colors.secondarySystemBackground),
        )

        CupertinoText(
            username,
            maxLines = 2
        )

        Spacer(Modifier.width(4.dp))

        Icon(
            contentDescription = null,
            imageVector = CupertinoIcons.Default.ChevronDown,
            tint = Colors.secondarySystemBackground,
        )
    }
}