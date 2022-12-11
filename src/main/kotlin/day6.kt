fun day6(): Pair<Int, Int> {
    val input = getResource("day6.txt").readText()

    return Pair(firstUniqueRunOf(4, input), firstUniqueRunOf(14, input))
}

private fun firstUniqueRunOf(size: Int, input: String) = input.windowed(size)
    .indexOfFirst { it.toSet().count() == size } + size