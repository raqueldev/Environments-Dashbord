package environment

import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.jackson.jackson
import io.ktor.routing.Routing
import environment.services.DatabaseConnection
import environment.web.environments


fun Application.main() {

    try {
        DatabaseConnection(
                "jdbc:sqlite:environments_monitoring.db",
                "org.sqlite.JDBC",
                true
        )

        install(DefaultHeaders)
        install(ContentNegotiation) {
            jackson {
                configure(SerializationFeature.INDENT_OUTPUT, true)
                setDefaultPrettyPrinter(DefaultPrettyPrinter().apply {
                    indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
                    indentObjectsWith(DefaultIndenter("  ", "\n"))
                })
            }
        }

        install(Routing) {
            environments()
        }

    } catch (e: Exception){
        print("Opps... something went wrong: ${e.message}")
    }
}




