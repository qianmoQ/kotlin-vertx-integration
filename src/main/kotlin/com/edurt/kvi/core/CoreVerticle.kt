package com.edurt.kvi.core

import com.edurt.kvi.router.index
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.core.http.HttpServerResponse
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext

class CoreVerticle : AbstractVerticle() {

    override fun start() {
        val router = createRouter(vertx)

        // go to index page
        index(router)

        vertx.createHttpServer().requestHandler { handler -> router.accept(handler) }.listen(8080)

//        val port = config().getInteger("http.port", 8080)
//        vertx.createHttpServer()
//                .requestHandler { router.accept(it) }
//                .listen(port) { result ->
//                    if (result.succeeded()) {
//                        startFuture?.complete()
//                    } else {
//                        startFuture?.fail(result.cause())
//                    }
//                }
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

    fun createRouter(v: Vertx): Router {
        var router = Router.router(v)
        router.route("/").handler { c -> c.response().end("Hello Kotlin Vertx Integration!") }
        router.route("/index").handler { c -> c.response().html().end("Hello Kotlin Vertx Integration Page!") }
        return router
    }

    fun HttpServerResponse.html(): HttpServerResponse {
        return this.putHeader("content-type", "text/html")
    }

}