package Environment

import io.ktor.routing.routing
import Environment.dao.*
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.Routing


fun Application.main () {

    val dbStorage = EnvironmentDatabase() //TODO set database

    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
            create()
        }
    }
    install(Routing)


    routing {
        environments(dbStorage)
    }
}