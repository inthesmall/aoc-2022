import kotlin.math.max

fun day8(): Pair<Int, Int> {
    val lines = getLines("day8.txt")
    val grid = lines.map { it.split("").filter(String::isNotBlank).map(String::toInt) }
    var visibleTrees = 4 * grid.size - 4
    var maxScenicScore = 0

    for (row in 1..(grid.size - 2)) {
        for (col in 1..(grid.size - 2)) {
            if (isVisible(grid, row, col)) {
                visibleTrees += 1
            }
            maxScenicScore = max(maxScenicScore, scenicScore(grid, row, col))
        }
    }

    return Pair(visibleTrees, maxScenicScore)
}

private fun isVisible(grid: List<List<Int>>, row: Int, col: Int): Boolean {
    val height = grid[row][col]

    // up
    if (grid.slice(0 until row).map { it[col] }.none { it >= height }) return true

    // down
    if (grid.slice(row + 1 until grid.size).map { it[col] }.none { it >= height }) return true

    // left
    if (grid[row].slice(0 until col).none { it >= height }) return true

    // right
    if (grid[row].slice(col + 1 until grid.size).none { it >= height }) return true

    return false
}

fun scenicScore(grid: List<List<Int>>, row: Int, col: Int): Int {
    val height = grid[row][col]

    val up = grid.slice(0 until row).map { it[col] }.reversed().indexOfFirst { it >= height }
    val up2 = if (up == -1) row else up + 1
    val down = grid.slice(row + 1 until grid.size).map { it[col] }.indexOfFirst { it >= height }
    val down2 = if (down == -1) (grid.size - (row + 1)) else down + 1
    val left = grid[row].slice(0 until col).reversed().indexOfFirst { it >= height }
    val left2 = if (left == -1) col else left + 1
    val right = grid[row].slice(col + 1 until grid.size).indexOfFirst { it >= height }
    val right2 = if (right == -1) (grid.size - (col+1)) else right + 1

    println("row: $row, col: $col, height: $height $up2 $down2 $left2 $right2")

    return up2 * down2 * left2 * right2
}