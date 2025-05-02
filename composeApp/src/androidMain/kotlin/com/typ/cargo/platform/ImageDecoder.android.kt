package com.typ.cargo.platform

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

actual object ImageDecoder {
    actual fun decodeFrameBytes(bytes: ByteArray): Result<ImageBitmap> {
        return try {
            Result.success(
                BitmapFactory
                    .decodeByteArray(
                        bytes,
                        0,
                        bytes.size
                    )!!
                    .asImageBitmap()
            )
        } catch (e: Exception) {
            return Result.failure(Exception("Can't decode video frame"))
        }
    }
}