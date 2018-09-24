package Environment

import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main (args: Array<String>) {

    val env = Environment(
            "Stage",
            0,
            "http://theurlofmyenvironment",
            "192.168.10.123",
            "This is the testing environment")

    val server = embeddedServer(Netty, port = 8000) {
        routing {
            get("/") {
                call.respondText("Welcome to Environments Dashbard")
            }
        }
    }
    server.start(wait=true)
}