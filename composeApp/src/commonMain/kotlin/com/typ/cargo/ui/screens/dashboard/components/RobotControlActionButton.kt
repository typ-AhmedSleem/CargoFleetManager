package com.typ.cargo.ui.screens.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import io.github.alexzhirkevich.cupertino.CupertinoIcon
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme

@Composable
internal fun RobotControlActionButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .clip(RoundedCornerShape(16.dp))
            .background(CupertinoTheme.colorScheme.systemBackground)
            .clickable(
                enabled = onClick != null,
                onClick = { onClick?.invoke() }
            )
            .padding(
                vertical = 24.dp,
                horizontal = 24.dp
            )
    ) {
        CupertinoIcon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier.size(48.dp)
        )
        Spacer(Modifier.height(16.dp))
        CupertinoText(
            text = title,
            maxLines = 3,
            style = CupertinoTheme.typography.title3,
            color = CupertinoTheme.colorScheme.label,
        )
    }
}