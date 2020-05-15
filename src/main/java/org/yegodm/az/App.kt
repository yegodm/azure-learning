package org.yegodm.az

import io.vertx.core.Vertx
import io.vertx.core.logging.LoggerFactory
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import kotlin.reflect.jvm.javaMethod


val log = LoggerFactory.getLogger(::main.javaMethod!!.declaringClass)

fun main() {
    log.info("Starting service...")
    val vertx = Vertx.vertx()
    val port = when (System.getProperty("env", "PROD")) {
        "DEV", "dev" -> 8080
        else -> 80
    }
    val server = vertx.createHttpServer()
    val router = Router.router(vertx)
        .apply {
            route("/teams/test")
                .consumes("application/json")
                .handler(RoutingContext::testTab)
            post("/teams/discover")
                .consumes("application/json")
                .handler(BodyHandler.create())
                .handler(RoutingContext::discover)
            route("/teams")
                .handler(RoutingContext::appInfo)
        }


    log.info("Listening at $port")
    server
        .requestHandler(router)
        .listen(port)
}

fun RoutingContext.appInfo() =
    response().end("Teams Test App")

fun RoutingContext.testTab() =
    response()
        .setStatusCode(200)
        .end("Nothing here yet")

fun RoutingContext.discover() {
    log.info("Payload: ${this.bodyAsJson}")
    response().end("Done.")
}