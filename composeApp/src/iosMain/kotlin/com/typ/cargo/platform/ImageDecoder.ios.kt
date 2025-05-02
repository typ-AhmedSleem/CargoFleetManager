package com.typ.cargo.platform

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image

actual object ImageDecoder {
    actual fun decodeFrameBytes(bytes: ByteArray): Result<ImageBitmap> {
        return try {
            Result.success(
                Image
                    .makeFromEncoded(bytes)
                    .toComposeImageBitmap()
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}