package com.typ.cargo.interfaces

import androidx.compose.runtime.Composable

interface BaseUiDataConverter<I, O> {
    @Composable
    fun convert(data: I): O
}