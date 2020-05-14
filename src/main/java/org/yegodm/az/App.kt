package org.yegodm.az

import io.vertx.core.Vertx
import io.vertx.core.http.HttpServerOptions

fun main() {
    val vertx = Vertx.vertx()
    val server = vertx.createHttpServer(
        HttpServerOptions()
            .setPort(8080)
    )
    server.requestHandler { req ->
        req.response().end("Hello!")
    }
    server.listen()
}
