package com.edurt.kvi.core

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext

class CoreVerticle : AbstractVerticle() {

    override fun start(startFuture: Future<Void>?) {
        val router = createRouter()
        val port = config().getInteger("http.port", 8080)
        vertx.createHttpServer()
                .requestHandler { router.accept(it) }
                .listen(port) { result ->
                    if (result.succeeded()) {
                        startFuture?.complete()
                    } else {
                        startFuture?.fail(result.cause())
                    }
                }
    }

    private fun createRouter() = Router.router(vertx).apply {
        get("/").handler(handlerRoot)
    }

    /**
     * create router instance
     */
    val handlerRoot = Handler<RoutingContext> { req ->
        req.response().end("Hello Kotlin Vertx Integration!")
    }

}