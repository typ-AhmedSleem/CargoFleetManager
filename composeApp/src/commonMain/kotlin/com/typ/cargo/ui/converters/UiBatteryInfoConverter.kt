package com.typ.cargo.ui.converters

import androidx.compose.runtime.Composable
import com.typ.cargo.interfaces.BaseUiDataConverter

object UiBatteryInfoConverter : BaseUiDataConverter<Int, String> {
    @Composable
    override fun convert(data: Int): String {
        return when (data) {
            in 0..30 -> "$data%\nNeeds charging!!"
            in 31..60 -> "$data%\nCharging soon"
            else -> "$data%"
        }
    }
}