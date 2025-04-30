package com.typ.cargo.ui.converters

import com.typ.cargo.interfaces.BaseUiDataConverter

object UiPercentageConverter : BaseUiDataConverter<Int, String> {
    override fun convert(data: Int): String {
        return "$data%"
    }
}