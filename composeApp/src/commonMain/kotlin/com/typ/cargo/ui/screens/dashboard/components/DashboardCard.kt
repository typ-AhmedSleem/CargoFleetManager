package com.typ.cargo.ui.screens.dashboard.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme

@Composable
internal fun DashboardCard(
    modifier: Modifier = Modifier.fillMaxWidth(),
    title: String,
    value: String,
    strokeColor: Color = CupertinoTheme.colorScheme.secondarySystemBackground,
    valueTextColor: Color = CupertinoTheme.colorScheme.label
) {
    DashboardCard(
        title = title,
        modifier = modifier,
        strokeColor= strokeColor,
        value = AnnotatedString(value),
        valueTextColor = valueTextColor
    )
}

@Composable
internal fun DashboardCard(
    title: String,
    value: AnnotatedString,
    modifier: Modifier = Modifier.fillMaxWidth(),
    valueTextColor: Color = CupertinoTheme.colorScheme.label,
    strokeColor: Color = CupertinoTheme.colorScheme.secondarySystemBackground,
) {
    Box(
        modifier = modifier
            .border(
                width= (2.5f).dp,
                color = strokeColor,
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .padding(16.dp)
            .animateContentSize()
    ) {
        Column {
            CupertinoText(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = CupertinoTheme.typography.title3,
                color = CupertinoTheme.colorScheme.secondaryLabel,
            )
            Spacer(Modifier.height(8.dp))
            AnimatedContent(
                targetState = value,
                transitionSpec = {
                    (scaleIn() + fadeIn()) togetherWith (scaleOut() + fadeOut())
                }
            ) {
                CupertinoText(
                    text = value,
                    fontSize = 24.sp,
                    color = valueTextColor,
                    overflow = TextOverflow.Ellipsis,
                    style = CupertinoTheme.typography.title2.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}