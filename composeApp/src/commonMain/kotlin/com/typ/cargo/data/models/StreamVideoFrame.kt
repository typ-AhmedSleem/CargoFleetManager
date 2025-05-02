package com.typ.cargo.data.models

import androidx.compose.ui.graphics.ImageBitmap

sealed class StreamVideoFrame {
    data object NoFrame : StreamVideoFrame()
    data object Buffering : StreamVideoFrame()
    data class Error(val errorMessage: String) : StreamVideoFrame()
    data class ReadyFrame(val frameBitmap: ImageBitmap) : StreamVideoFrame()
}