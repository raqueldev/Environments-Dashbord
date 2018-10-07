package environment.web

import environment.services.EnvironmentsRepository as repo
import io.ktor.application.call
import io.ktor.response.*
import io.ktor.routing.Route
import io.ktor.routing.get

fun Route.environments() {
    get("/") {
        call.respond(repo().getAllEnvironments())
    }
}