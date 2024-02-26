package com.costacodecraft.plugins

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World")
        }
        get("/users/joaquim") {
            call.respondText { "Hello, Joaquim" }
        }
        route("/books") {
            get("/") {
                call.respondText { "Get all books" }
            }
            get("/{id?}") {
                val id = call.parameters["id"]?.toLong()
                val responseText = if (id == null) "Not Found" else "Returning book with id $id"
                call.respondText { responseText }
            }
        }
        /**
         * Using wildcard
         * /photos will not work
         * but /photos/anything will work
         */
        get("/photos/*") {
            val path = call.request.path()
            call.respondText { "Get photos... $path" }
        }
        /**
         * Using tailcard
         * /videos will work
         * /videos/anything will work
         */
        get("/videos/{...}") {
            val path = call.request.path()
            call.respondText { "Get videos.... $path" }
        }
        get("/words/{params...}") {
            val params = call.parameters.getAll("params")
            call.respondText { "Got words: $params" }
        }
        // get the query parameter id
        get("/todo") {
            val id = call.request.queryParameters["id"]
            call.respondText { "Todo: $id" }
        }
    }
}
