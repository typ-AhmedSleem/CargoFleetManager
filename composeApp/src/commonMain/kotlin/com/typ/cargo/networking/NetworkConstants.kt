package com.typ.cargo.networking

const val CONNECTION_TIMEOUT = 30000L
const val DEFAULT_RETRY_DELAY = 1000L
const val DEFAULT_MAX_RETRIES = 15

const val AMR_DEFAULT_PORT = 2001
const val AMR_DEFAULT_IP = "0.0.0.0"
const val AMR_BASE_URL = "http://$AMR_DEFAULT_IP:$AMR_DEFAULT_PORT"

object Endpoints {
    val videoFeed = buildEndpoint("videoFeed")
}

fun buildEndpoint(endpoint: String) = "$AMR_BASE_URL/$endpoint"