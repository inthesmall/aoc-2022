fun day1(): Pair<Int, Int> {
    val input = getResource("day1.txt").readText()
    val elfLines = input.split("\r\n\r\n")
    val elfValues = elfLines.map { lines ->
        lines
            .split("\r\n")
            .sumOf { line -> Integer.parseInt(line) }
    }
    return Pair(elfValues.max(), elfValues.sortedDescending().take(3).sum())
}