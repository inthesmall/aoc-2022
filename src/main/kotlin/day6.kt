import java.io.File

fun day6() {
    val input = File("src/main/resources/day6.txt").readText()

    println("Day 6")
    println(input.windowed(4).indexOfFirst { it.toSet().count() == 4 } + 4)
    println(input.windowed(14).indexOfFirst { it.toSet().count() == 14 } + 14)
}