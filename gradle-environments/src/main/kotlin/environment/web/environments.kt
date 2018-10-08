package environment.web

import environment.services.EnvironmentsRepository as repo
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import io.ktor.request.receiveParameters
import io.ktor.response.*
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post

fun Route.environments() {
    get("/") {
        call.respond(repo().getAllEnvironments())
    }

    get("/{idOrName}") {
        val idOrName = call.parameters["idOrName"]
        if (idOrName.isNullOrEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "Missing parameter in the request")
        }
        call.respond(repo().getEnvironment(call.parameters["idOrName"]!!))
    }
}