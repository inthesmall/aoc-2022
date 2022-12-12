fun day3(): Pair<Int, Int> {
    val lowerScores = ('a'..'z').withIndex().associateBy({ it.value }, { it.index + 1 })
    val upperScores = ('A'..'Z').withIndex().associateBy({ it.value }, { it.index + 27 })
    val totalScores = lowerScores.plus(upperScores)

    val lines = getLines("day3.txt")
    val part1 = lines.map { findDuplicateItem(it) }.sumOf { totalScores[it] ?: 0 }
    val part2 = lines.chunked(3).map { badgeFromGroup(it) }.sumOf { totalScores[it] ?: 0 }


    return Pair(part1, part2)
}

fun findDuplicateItem(contents: String): Char {
    return contents.chunked(contents.count() / 2)
        .map(String::toSet)
        .reduce(Set<Char>::intersect)
        .first()
}

fun badgeFromGroup(group: List<String>): Char {
    return group.map(String::toSet)
        .reduce(Set<Char>::intersect)
        .first()
}