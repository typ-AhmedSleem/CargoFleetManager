package com.typ.cargo.interfaces

interface BaseUiDataConverter<I, O> {
    fun convert(data: I): O
}