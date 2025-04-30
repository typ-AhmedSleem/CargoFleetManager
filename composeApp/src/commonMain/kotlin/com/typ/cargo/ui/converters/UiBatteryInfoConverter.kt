package com.typ.cargo.ui.converters

import com.typ.cargo.interfaces.BaseUiDataConverter

object UiBatteryInfoConverter : BaseUiDataConverter<Int, String> {
    override fun convert(data: Int): String {
        return when (data) {
            in 0..30 -> "$data%\nNeeds charging!!"
            in 31..60 -> "$data%\nCharging soon"
            else -> "$data%"
        }
    }
}