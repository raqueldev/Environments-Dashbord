package environment.model

import org.jetbrains.exposed.sql.Table

data class EnvironmentResponse(
    val id: Int,
    val name: String,
    val link: String,
    val description: String?,
    val ip: String
)

object Environments: Table() {
    val id = integer("id").autoIncrement().uniqueIndex().primaryKey()
    val name = varchar("name", 50)
    val link = varchar("link", 100)
    val description = varchar("description", 100).nullable()
    val ip = varchar("ip", 15)
}