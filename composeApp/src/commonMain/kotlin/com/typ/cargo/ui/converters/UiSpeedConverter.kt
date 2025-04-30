package com.typ.cargo.ui.converters

import com.typ.cargo.interfaces.BaseUiDataConverter
import kotlin.math.absoluteValue

object UiSpeedConverter : BaseUiDataConverter<Int, String> {
    override fun convert(data: Int): String {
        return if (data == 0) {
            "0 m/s\nAMR is not moving"
        } else if (data > 0) {
            "$data m/s\nRotating forward"
        } else {
            "${data.absoluteValue} m/s\nRotating backward"
        }
    }
}