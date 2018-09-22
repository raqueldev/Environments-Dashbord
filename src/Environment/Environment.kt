package Environment

class Environment (
        private var name: String = "",
        private var status: Int = 0,
        private var link: String = "",
        private var ip: String = "",
        private var description: String? = "",
        private var dataBaseIp: List<String>? = null) {

    init {
        println("Creating $name environment")
    }
}