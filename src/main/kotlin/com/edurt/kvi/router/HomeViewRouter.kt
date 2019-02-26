package com.edurt.kvi.router

import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.templ.ThymeleafTemplateEngine
import org.thymeleaf.templatemode.TemplateMode

class HomeViewRouter

fun index(r: Router) {
    val engine = ThymeleafTemplateEngine.create().setMode(TemplateMode.HTML)
    r.get("/index.html").handler { c ->
        render(c, engine, "templates/index.html")
    }
}

fun render(c: RoutingContext, engine: ThymeleafTemplateEngine, templ: String) {
    engine.render(c, templ) { res ->
        if (res.succeeded()) {
            c.response().end(res.result())
        } else {
            c.fail(res.cause())
        }
    }
}