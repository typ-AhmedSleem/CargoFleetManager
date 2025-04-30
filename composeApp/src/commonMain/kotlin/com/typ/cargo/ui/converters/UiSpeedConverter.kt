package com.typ.cargo.ui.converters

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.typ.cargo.interfaces.BaseUiDataConverter
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme
import kotlin.math.absoluteValue

object UiSpeedConverter : BaseUiDataConverter<Int, AnnotatedString> {
    @Composable
    override fun convert(data: Int): AnnotatedString {
        return buildAnnotatedString {
            withStyle(
                SpanStyle(
                    color = when {
                        data == 0 -> CupertinoTheme.colorScheme.label
                        else -> CupertinoTheme.colorScheme.accent
                    },
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("${data.absoluteValue} ")
            }
            withStyle(
                SpanStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = CupertinoTheme.colorScheme.label,
                )
            ) {
                append("m/s")
            }
            withStyle(
                SpanStyle(fontWeight = FontWeight.Normal)
            ) {
                append(
                    if (data == 0) "AMR is not moving"
                    else if (data > 0) "\nRotating forward"
                    else "\nRotating backward"
                )
            }
        }
    }
}