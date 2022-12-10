import java.io.File

@Suppress("unused")
fun day1() {
    val input = File("src/main/resources/day1.txt").readText()
    val elfLines = input.split("\r\n\r\n")
    val elfValues = elfLines.map { lines ->
        lines
            .split("\r\n")
            .sumOf { line -> Integer.parseInt(line) }
    }
    println(elfValues.max())
    println(elfValues.sortedDescending().take(3).sum())
}