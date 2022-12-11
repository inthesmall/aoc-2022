import java.lang.Integer.parseInt

typealias Stacks = List<ArrayDeque<Char>>

fun day5(): Pair<String, String> {
    val lines = getLines("day5.txt")
    val bottomRow = lines.indexOfLast { it.contains("[") }
    val firstMove = lines.indexOfFirst { it.contains("move") }
    val rows = lines.take(bottomRow + 1)
    val stackNumsRow = lines[bottomRow + 1]
    val stackNums = buildStackNums(stackNumsRow)
    val partOneStacks = buildStacks(rows, stackNums)
    val partTwoStacks = buildStacks(rows, stackNums)

    val moveLines = lines.slice(firstMove..lines.lastIndex)
    val moves = buildMoves(moveLines)

    for (move in moves) {
        repeat(move[0]) { _ -> performPartOneMove(move, partOneStacks) }
        performPartTwoMove(move, partTwoStacks)
    }

    return Pair(generateAnswerFromStacks(partOneStacks), generateAnswerFromStacks(partTwoStacks))
}

private fun generateAnswerFromStacks(stacks: Stacks): String {
    return stacks.flatMap { it.takeLast(1) }.joinToString("")
}

private fun buildStackNums(stackNumsRow: String) =
    stackNumsRow.split("\\s+".toRegex()).filter { it.isNotBlank() }.map { parseInt(it) }

private fun buildMoves(moveLines: List<String>): List<ArrayList<Int>> {
    val pattern = Regex("move (\\d+) from (\\d+) to (\\d+)")
    val moves = moveLines.mapNotNull { line -> pattern.find(line) }.map {
        arrayListOf(
            parseInt(it.groups[1]?.value), parseInt(it.groups[2]?.value), parseInt(it.groups[3]?.value)
        )
    }
    return moves
}


private fun buildStacks(
    rows: List<String>,
    stackNums: List<Int>,
): Stacks {
    val stacks = List(stackNums.count()) { _ -> ArrayDeque<Char>() }
    for (row in rows) {
        for (stackNum in stackNums) {
            val index = stackNumToIndex(stackNum)
            if (row.length > index && row[index].isLetter()) {
                stacks[stackNum - 1].addFirst(row[index])
            }
        }
    }
    return stacks
}

private fun stackNumToIndex(stackNum: Int): Int {
    return (4 * (stackNum - 1)) + 1
}

private fun performPartOneMove(move: List<Int>, stacks: Stacks) {
    val origin = move[1] - 1
    val destination = move[2] - 1
    val item = stacks[origin].removeLast()
    stacks[destination].addLast(item)
}

private fun performPartTwoMove(move: List<Int>, stacks: Stacks) {
    val origin = move[1] - 1
    val destination = move[2] - 1
    val items = stacks[origin].takeLast(move[0])
    stacks[destination].addAll(items)
    repeat(move[0]) { _ -> stacks[origin].removeLast() }
}