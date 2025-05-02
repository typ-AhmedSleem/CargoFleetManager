package com.typ.cargo.networking

import io.ktor.client.HttpClient

fun createDefaultHttpClient(): HttpClient {
    return HttpClient {
        // todo: configure the http client here (if needed)
//        expectSuccess = true
        /*install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }*/
    }
}