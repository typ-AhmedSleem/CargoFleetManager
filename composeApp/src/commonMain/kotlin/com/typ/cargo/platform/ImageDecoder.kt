package com.typ.cargo.platform

import androidx.compose.ui.graphics.ImageBitmap

expect object ImageDecoder {
    fun decodeFrameBytes(bytes: ByteArray): Result<ImageBitmap>
}