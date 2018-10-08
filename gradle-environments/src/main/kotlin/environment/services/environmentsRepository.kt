package environment.services

import environment.model.EnvironmentResponse
import environment.model.Environments
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class EnvironmentsRepository {

    fun getAllEnvironments(): List<EnvironmentResponse> {

        var environments: List<EnvironmentResponse> = mutableListOf()

        transaction {
            environments = Environments.selectAll().map { toEnvironment(it) }
        }

        return environments
    }

    fun toEnvironment(row: ResultRow): EnvironmentResponse = EnvironmentResponse(
            id = row[Environments.id],
            name = row[Environments.name].toString(),
            link = row[Environments.link].toString(),
            description = row[Environments.description].toString(),
            ip = row[Environments.ip].toString()
    )
}