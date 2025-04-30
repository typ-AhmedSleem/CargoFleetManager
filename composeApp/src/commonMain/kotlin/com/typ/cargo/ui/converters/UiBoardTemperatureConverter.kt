package com.typ.cargo.ui.converters

import com.typ.cargo.interfaces.BaseUiDataConverter

object UiBoardTemperatureConverter : BaseUiDataConverter<Int, String> {
    override fun convert(data: Int): String {
        return "${
            when {
                data in 28..30 -> "Good"
                data in 31..40 -> "Needs attention"
                data >= 41 -> "Danger! Needs cooldown"
                else -> ""
            }
        } ($data°)"
    }
}