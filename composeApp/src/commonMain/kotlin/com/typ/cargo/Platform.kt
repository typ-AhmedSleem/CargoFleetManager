package com.typ.cargo

sealed class Platform {
    data object Android : Platform()
    data object Desktop : Platform()
    data object IOS : Platform()
    data object Web : Platform()
}

expect fun getPlatform(): Platform