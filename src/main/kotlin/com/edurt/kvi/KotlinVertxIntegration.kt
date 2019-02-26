package com.edurt.kvi

import com.edurt.kvi.core.CoreVerticle
import io.vertx.core.Vertx

class KotlinVertxIntegration

fun main(args: Array<String>) {
    val vertx = Vertx.vertx()
    vertx.deployVerticle(CoreVerticle::class.java.name)
}