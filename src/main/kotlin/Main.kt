import java.io.File
import java.lang.Integer.parseInt

fun main() {
    day1()
    println()
    day2()
    println()
    day3()
    println()
}

private fun day1() {
    val input = File("src/main/resources/day1.csv").readText()
    val elfLines = input.split("\r\n\r\n")
    val elfValues = elfLines.map { lines ->
        lines
            .split("\r\n")
            .sumOf { line -> parseInt(line) }
    }
    println(elfValues.max())
    val elfMap = elfValues.withIndex().associateBy({ it.index }, { it.value })
    val sortedMap = elfMap.toSortedMap { i, j -> elfMap[j]!! - elfMap[i]!! }
    val topThreeElves = sortedMap.keys.take(3)
    val totalCals = topThreeElves.sumOf { elfMap[it] ?: 0 }
    println(totalCals)
}