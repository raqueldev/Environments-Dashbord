package environment.services

import environment.model.EnvironmentResponse
import environment.model.Environments
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class EnvironmentsRepository {

    fun getAllEnvironments(): List<EnvironmentResponse> = transaction {
            Environments.selectAll().map { toEnvironment(it) }
    }

    fun getEnvironment(idOrName: String) : List<EnvironmentResponse> = transaction {
            Environments.select { (Environments.id eq idOrName.toInt()) or (Environments.name eq idOrName) }.map { toEnvironment(it) }
    }

    private fun toEnvironment(row: ResultRow): EnvironmentResponse = EnvironmentResponse(
            id = row[Environments.id],
            name = row[Environments.name].toString(),
            link = row[Environments.link].toString(),
            description = row[Environments.description].toString(),
            ip = row[Environments.ip].toString()
    )
}