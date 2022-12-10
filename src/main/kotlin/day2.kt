import java.io.File

fun day2() {
    val movePoints = mapOf("X" to 1, "Y" to 2, "Z" to 3)
    val roundPoints = mapOf(
        "A X" to 3,
        "A Y" to 6,
        "A Z" to 0,
        "B X" to 0,
        "B Y" to 3,
        "B Z" to 6,
        "C X" to 6,
        "C Y" to 0,
        "C Z" to 3
    )

    val round2Points = mapOf(
        "A X" to 3,
        "A Y" to 4,
        "A Z" to 8,
        "B X" to 1,
        "B Y" to 5,
        "B Z" to 9,
        "C X" to 2,
        "C Y" to 6,
        "C Z" to 7
    )

    val lines = File("src/main/resources/day2.txt").readLines()

    val scoreForRounds = lines.sumOf { roundPoints[it] ?: 0 }
    val scoreForMoves = lines.sumOf { movePoints[it.takeLast(1)] ?: 0}

    val round2Score = lines.sumOf { round2Points[it] ?: 0 }


    println("Day 2")
    println(scoreForMoves + scoreForRounds)
    println(round2Score)
}