package Environment

import kotliquery.Row

data class Environment (
        val name: String = "",
        val status: Int = 0,
        val link: String = "",
        val ip: String = "",
        val description: String? = "",
        val dataBaseIp: List<String>? = null) {

    val toEnvironment: (Row) -> Environment = { row ->
        Environment(
                row.string("name"),
                row.int("status"),
                row.string("link"),
                row.string("ip"),
                row.string("description"))
    }

}