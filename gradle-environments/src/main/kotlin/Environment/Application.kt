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
import environment.model.Environments
import environment.web.environments
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

fun Application.main() {

    connectToDatabase("jdbc:sqlite:environments_monitoring.db", "org.sqlite.JDBC")
    initDatabase(true)

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
}

private fun connectToDatabase(connection: String, driver: String) {
    Database.connect(connection, driver)
    TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE
}

/**
 * Create the tables
 */
private fun initDatabase(isSampleDataLoad: Boolean) {
    transaction {

        addLogger(StdOutSqlLogger)
        create(Environments)

        if (isSampleDataLoad) {
            loadSampleData()
            print("All environments: ${Environments.selectAll()}")
        }
    }
}

/**
 * Add some initial sample values
 */
private fun loadSampleData() {
    Environments.insert {
        it[name] = "Development"
        it[ip] = "192.168.22.12"
        it[link] = "http://"
        it[description] = "My description"
    } get Environments.id

    Environments.insert {
        it[name] = "Stage"
        it[ip] = "192.168.22.13"
        it[link] = "http://"
        it[description] = "My description"
    } get Environments.id
}


