import kotlin.math.min

fun day7(): Pair<Int, Int> {
    val lines = getLines("day7.txt")

    var currentNode: Node? = null

    for (line in lines) {
        when {
            line == "$ cd .." -> currentNode = currentNode?.parent
            line.startsWith("$ cd") -> currentNode = cd(line, currentNode)
            line.first().isDigit() -> addFile(line, currentNode)
        }
    }
    val rootNode = getRootNode(currentNode)

    val partOneAnswer = partOne(rootNode)
    val partTwoAnswer = partTwo(rootNode)

    return Pair(partOneAnswer, partTwoAnswer)
}

private fun cd(line: String, currentNode: Node?): Node {
    val dir = line.substringAfter("$ cd ")
    return currentNode?.nodes?.get(dir) ?: run {
        val newNode = Node(currentNode)
        currentNode?.nodes?.set(dir, newNode)
        newNode
    }
}

private fun addFile(line: String, currentNode: Node?) {
    val size = line.takeWhile { it.isDigit() }.toInt()
    var node = currentNode
    while (node != null) {
        node.size += size
        node = node.parent
    }
}

private fun getRootNode(currentNode: Node?): Node {
    var node = currentNode
    while (node?.parent != null) {
        node = node.parent
    }
    return node!!
}

private fun partOne(rootNode: Node): Int {
    var partOneAnswer = 0
    walkTree(rootNode) { node ->
        if (node.size <= 100000) {
            partOneAnswer += node.size
        }
    }
    return partOneAnswer
}

private fun partTwo(rootNode: Node): Int {
    val spaceFree = 70000000 - rootNode.size
    val spaceRequired = 30000000 - spaceFree

    var amountToDelete = 70000000

    walkTree(rootNode) { node ->
        if (node.size > spaceRequired) {
            amountToDelete = min(node.size, amountToDelete)
        }
    }
    return amountToDelete
}

fun walkTree(node: Node, action: (Node) -> Unit) {
    for ((_, childNode) in node.nodes) {
        walkTree(childNode, action)
    }
    action(node)
}

class Node(val parent: Node? = null) {
    var nodes: HashMap<String, Node> = HashMap()
    var size: Int = 0
    override fun toString(): String {
        return "Node(nodes=$nodes, size=$size)"
    }
}

