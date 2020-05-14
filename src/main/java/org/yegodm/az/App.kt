package org.yegodm.az

import io.vertx.core.Vertx
import io.vertx.core.http.HttpServerOptions

fun main() {
    val vertx = Vertx.vertx()
    val port = 8080
    val server = vertx.createHttpServer(
        HttpServerOptions()
            .setPort(port)
    )
    server.requestHandler { req ->
        req.response().end("Hello, ${req.remoteAddress()}!")
    }
    println("Listening at $port")
    server.listen()
}
