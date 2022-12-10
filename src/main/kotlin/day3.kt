import java.io.File

fun day3() {
    val lowerScores = ('a'..'z').withIndex().associateBy({ it.value.toString() }, { it.index + 1 })
    val upperScores = ('A'..'Z').withIndex().associateBy({ it.value.toString() }, { it.index + 27 })
    val totalScores = lowerScores.plus(upperScores)

    val lines = File("src/main/resources/day3.txt").readLines()
    val duplicates = lines.map { findDuplicateItem(it) }
    val scores = duplicates.sumOf { totalScores[it] ?: 0 }

    val part2 = lines.chunked(3).map { badgeFromGroup(it) }.sumOf { totalScores[it] ?: 0 }


    println("Day 3")
    println(scores)
    println(part2)
}

fun findDuplicateItem(contents: String): String {
    val compartmentSize = contents.count() / 2
    val partA = contents.take(compartmentSize).split("").filterNot { it.isBlank() }.toSet()
    val partB = contents.takeLast(compartmentSize).split("").filterNot { it.isBlank() }.toSet()
    return partA.intersect(partB).firstOrNull() ?: ""
}

fun badgeFromGroup(group: List<String>): String {
    return group.map { singleLine -> singleLine.split("").filterNot { it.isBlank() }.toSet() }
        .reduce {setA, setB -> setA.intersect(setB)}
        .firstOrNull() ?: throw RuntimeException()
}