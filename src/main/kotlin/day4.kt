import java.lang.Integer.parseInt

fun day4(): Pair<Int, Int> {
    val lines = getLines("day4.txt")
    val pairs = lines.map { toPair(it) }

    val fullyContainedPairsCount = pairs.count { isFullyContained(it) }
    val overlappingPairsCount = pairs.count { overlaps(it) }

    return Pair(fullyContainedPairsCount, overlappingPairsCount)
}

fun toPair(line: String): List<List<Int>> {
    return line.split(",").map { it.split("-").map(::parseInt) }
}

fun isFullyContained(pairs: List<List<Int>>): Boolean {
    return (pairs[0][0] >= pairs[1][0] && pairs[0][1] <= pairs[1][1]) ||
            (pairs[0][0] <= pairs[1][0] && pairs[0][1] >= pairs[1][1])
}

fun overlaps(pairs: List<List<Int>>): Boolean {
    return (pairs[0][0] <= pairs[1][0] && pairs[0][1] >= pairs[1][0]) ||
            (pairs[0][0] <= pairs[1][1] && pairs[0][1] >= pairs[1][1]) ||
            isFullyContained(pairs)
}