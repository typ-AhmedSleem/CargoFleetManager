package com.typ.cargo.ui.screens.dashboard.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.typ.cargo.ui.converters.UiAngleConverter
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme

@Composable
internal fun RobotHeadingCard(
    modifier: Modifier = Modifier.fillMaxWidth(),
    robotHeading: Int,
) {
    DashboardCard(
        modifier = modifier,
        title = "Robot Heading",
        value = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    color = CupertinoTheme.colorScheme.accent,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(UiAngleConverter.convert(robotHeading))
            }
            withStyle(
                SpanStyle(fontWeight = FontWeight.Normal)
            ) {
                append("\n")
                append("from North")
            }
        }
    )
}