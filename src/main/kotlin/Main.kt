import java.io.File
import java.lang.Integer.parseInt

fun main() {
    day1()
    println()
    day2()
    println()
    day3()
    println()
    day4()
    println()
    day5()
    println()
}

private fun day1() {
    val input = File("src/main/resources/day1.txt").readText()
    val elfLines = input.split("\r\n\r\n")
    val elfValues = elfLines.map { lines ->
        lines
            .split("\r\n")
            .sumOf { line -> parseInt(line) }
    }
    println(elfValues.max())
    println(elfValues.sortedDescending().take(3).sum())
}