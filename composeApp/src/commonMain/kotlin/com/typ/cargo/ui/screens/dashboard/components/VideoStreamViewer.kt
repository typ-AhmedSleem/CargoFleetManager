package com.typ.cargo.ui.screens.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.typ.cargo.data.models.StreamVideoFrame
import com.typ.cargo.ui.theming.Colors
import io.github.alexzhirkevich.cupertino.CupertinoActivityIndicator
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.ExperimentalCupertinoApi
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme

@Composable
internal fun VideoStreamViewer(
    modifier: Modifier,
    videoFrame: StreamVideoFrame,
) {
    var lastSuccessfulFrame by remember {
        mutableStateOf(ImageBitmap(1, 1))
    }
    var showingBlur by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(videoFrame) {
        if (videoFrame is StreamVideoFrame.ReadyFrame) {
            lastSuccessfulFrame = videoFrame.frameBitmap
        }
    }

    Box(
        modifier = modifier
            .border(
                width = 2.dp,
                shape = RoundedCornerShape(16.dp),
                color = Colors.secondarySystemBackground,
            )
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .matchParentSize()
                .blur((if (showingBlur) 15 else 0).dp),
            contentDescription = null,
            bitmap = lastSuccessfulFrame,
            contentScale = ContentScale.Crop,
        )

        when (videoFrame) {
            is StreamVideoFrame.NoFrame -> {
                showingBlur = false
            }

            is StreamVideoFrame.Buffering -> {
                showingBlur = true
                VideoStreamBufferingIndicator(
                    modifier = Modifier.matchParentSize()
                )
            }

            is StreamVideoFrame.Error -> {
                showingBlur = true
                VideoStreamErrorIndicator(
                    modifier = Modifier.matchParentSize(),
                    errorMessage = videoFrame.errorMessage
                )
            }

            else -> {
                showingBlur = false
            }
        }
    }
}

@Composable
@OptIn(ExperimentalCupertinoApi::class)
private fun VideoStreamBufferingIndicator(
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            alignment = Alignment.CenterVertically,
            space = 12.dp,
        )
    ) {
        CupertinoActivityIndicator(
            size = 64.dp,
            color = Colors.warning
        )
        CupertinoText(
            text = "Buffering...",
            color = Colors.warning,
            textAlign = TextAlign.Center,
            style = CupertinoTheme.typography.title1
        )
    }
}

@Composable
private fun VideoStreamErrorIndicator(
    modifier: Modifier,
    errorMessage: String
) {
    Box(modifier, contentAlignment = Alignment.Center) {
        CupertinoText(
            text = errorMessage,
            color = Colors.error,
            textAlign = TextAlign.Center,
            style = CupertinoTheme.typography.title3
        )
    }
}
