package com.typ.cargo.ui.converters

import androidx.compose.runtime.Composable
import com.typ.cargo.interfaces.BaseUiDataConverter

object UiPercentageConverter : BaseUiDataConverter<Int, String> {

    @Composable
    override fun convert(data: Int): String {
        return "$data%"
    }
}