package com.typ.cargo.ui.converters

import androidx.compose.runtime.Composable
import com.typ.cargo.interfaces.BaseUiDataConverter

object UiAngleConverter : BaseUiDataConverter<Int, String> {
    @Composable
    override fun convert(data: Int): String {
        return "$data°"
    }
}