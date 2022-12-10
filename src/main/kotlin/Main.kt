fun main() {
    for (day in 1..25) {
        val clazz = Class.forName(::main.javaClass.module, "Day${day}Kt")
        if (clazz != null) {
            println("Day $day")
            clazz.getMethod("day${day}")
                .invoke(null)
            println()
        }
    }
}