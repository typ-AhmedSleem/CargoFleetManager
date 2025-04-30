package com.typ.cargo.ui.converters

import com.typ.cargo.interfaces.BaseUiDataConverter

object UiAngleConverter : BaseUiDataConverter<Int, String> {
    override fun convert(data: Int): String {
        return "$data°"
    }
}