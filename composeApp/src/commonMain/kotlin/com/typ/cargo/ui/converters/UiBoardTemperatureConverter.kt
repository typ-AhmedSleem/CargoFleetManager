package com.typ.cargo.ui.converters

import androidx.compose.runtime.Composable
import com.typ.cargo.interfaces.BaseUiDataConverter

object UiBoardTemperatureConverter : BaseUiDataConverter<Int, String> {
    @Composable
    override fun convert(data: Int): String {
        return "$data°\n${
            when {
                data in 28..35 -> "Good"
                data in 36..40 -> "Needs attention"
                data >= 41 -> "Danger! Needs cooldown"
                else -> ""
            }
        }"
    }
}