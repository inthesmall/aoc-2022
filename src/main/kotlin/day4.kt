import java.io.File
import java.lang.Integer.parseInt

fun day4() {
    val lines = File("src/main/resources/day4.txt").readLines()
    val pairs = lines.map { toPair(it) }

    val fullyContainedPairsCount = pairs.count { isFullyContained(it) }
    val overlappingPairsCount = pairs.count { overlaps(it) }

    println("Day 4")
    println(fullyContainedPairsCount)
    println(overlappingPairsCount)
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