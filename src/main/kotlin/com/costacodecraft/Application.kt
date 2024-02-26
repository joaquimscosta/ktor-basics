package com.costacodecraft

import com.costacodecraft.plugins.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>) {
    EngineMain.main(args)
// here for reference: using configuration file instead application.conf
//    embeddedServer(
//        Netty,
//        port = 8080,
//        host = "0.0.0.0",
//        module = Application::module
//    ).start(wait = true)
}

@Suppress("unused")
fun Application.module() {
    install(IgnoreTrailingSlash)
    configureMonitoring()
    configureRouting()

//    install(Routing) {
//        route("/", HttpMethod.Get) {
//            handle {
//                call.respondText { "Hello From routing" }
//            }
//        }
//    }
}
