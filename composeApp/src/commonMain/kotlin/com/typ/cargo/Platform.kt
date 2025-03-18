package com.typ.cargo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform