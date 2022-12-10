import java.io.File

@Suppress("unused")
fun day6() {
    val input = File("src/main/resources/day6.txt").readText()

    println(firstUniqueRunOf(4, input))
    println(firstUniqueRunOf(14, input))
}

private fun firstUniqueRunOf(size: Int, input: String) = input.windowed(size)
    .indexOfFirst { it.toSet().count() == size } + size