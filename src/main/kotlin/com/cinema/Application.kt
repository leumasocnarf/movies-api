package com.cinema

import com.cinema.dao.entities.DatabaseFactory
import com.cinema.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {
    DatabaseFactory.init(environment)
    configureSerialization()
    configureHTTP()
    configureRouting()
}
