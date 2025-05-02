package com.typ.cargo.workers

import com.typ.cargo.data.models.StreamVideoFrame
import com.typ.cargo.networking.DEFAULT_MAX_RETRIES
import com.typ.cargo.networking.DEFAULT_RETRY_DELAY
import com.typ.cargo.platform.ImageDecoder
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.isActive
import kotlin.coroutines.CoroutineContext

class StreamWatcher(
    private val client: HttpClient,
    private val streamUrl: String,
) {
    var isWatching: Boolean = false
        private set

    init {
        println("StreamWatcher: Initialized with URL: '$streamUrl'. isWatching: '$isWatching'.")
    }

    fun subscribeToVideoStream(flowContext: CoroutineContext = Dispatchers.Default): Flow<StreamVideoFrame> {
        /*        if (isWatching) {
                    return channelFlow {
                        close(Exception("Already watching video stream."))
                    }
                }*/

        var retriesLeft = DEFAULT_MAX_RETRIES
        return channelFlow {
            isWatching = true
            send(StreamVideoFrame.Buffering)
            delay(1000L)
            while (client.isActive && isActive) {
                try {
                    val frameBytes = fetchFrameBytes(client)
                    ImageDecoder.decodeFrameBytes(frameBytes)
                        .onSuccess { imageBitmap ->
                            send(StreamVideoFrame.ReadyFrame(imageBitmap))
                        }
                        .onFailure {
                            send(StreamVideoFrame.Buffering)
                            delay(100)
                        }
                } catch (e: Exception) {
                    // * Handle exception
                    retriesLeft -= 1
                    e.printStackTrace()
                    send(StreamVideoFrame.Buffering)
                    // * Check if we've run out of retries
                    if (retriesLeft <= 0) {
                        send(
                            StreamVideoFrame.Error(
                                errorMessage = e.message
                                    ?: "Can't fetch next frame from stream."
                            )
                        )
                        isWatching = false
                        break
                    }
                    delay(DEFAULT_RETRY_DELAY)
                }
            }
        }.flowOn(flowContext)
            .onCompletion {
                client.close()
                isWatching = false
                println("StreamWatcher: Completed or Cancelled. Reason: ${it?.message ?: "Unknown"}")
            }
    }

    // ** Helper function ** //
    private suspend fun fetchFrameBytes(client: HttpClient): ByteArray {
        return client
            .get(streamUrl)
            .body()
    }
}