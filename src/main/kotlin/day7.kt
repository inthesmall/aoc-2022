import java.util.Collections.min

fun day7(): Pair<Int, Int> {
    val lines = getLines("day7.txt")

    var currentNode: Node? = null

    for (line in lines) {
        if (line == "$ cd ..") {
            currentNode = currentNode?.parent
        } else if (line.startsWith("$ cd")) {
            val dir = line.substringAfter("$ cd ")
            if (currentNode?.nodes?.containsKey(dir) == true) {
                currentNode = currentNode.nodes[dir]
            } else {
                val newNode = Node(currentNode)
                currentNode?.nodes?.set(dir, newNode)
                currentNode = newNode
            }
        } else if (line.first().isDigit()) {
            val size = line.split(" ").first().toInt()
            currentNode!!.size += size
        }
    }

    while (currentNode?.parent != null) {
        currentNode = currentNode.parent
    }
    val rootNode = currentNode!!


    // node walk
    walkTree(rootNode) { node ->
        if (node.parent != null) {
            node.parent.size += node.size
        }
    }

    var partOneAnswer = 0

    walkTree(rootNode) { node ->
        if (node.size <= 100000) {
            partOneAnswer += node.size
        }
    }

    val spaceFree = 70000000 - rootNode.size
    val spaceRequired = 30000000 - spaceFree

    var amountToDelete = 70000000

    walkTree(rootNode) {node ->
        if (node.size > spaceRequired) {
            amountToDelete = kotlin.math.min(node.size, amountToDelete)
        }
    }


    return Pair(partOneAnswer, amountToDelete)
}

class Node(val parent: Node? = null) {
    var nodes: HashMap<String, Node> = HashMap()
    var size: Int = 0

    override fun toString(): String {
        return "Node(nodes=$nodes, size=$size)"
    }
}

fun walkTree(node: Node, action: (Node) -> Unit) {
    for ((_, childNode) in node.nodes) {
        walkTree(childNode, action)
    }
    action(node)
}

