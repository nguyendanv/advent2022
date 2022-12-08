fun main() {
    data class Node(val name: String) {
        private var sizeOfFiles: Int = 0
        private val children: MutableMap<String, Node> = mutableMapOf()

        fun addFile(size: Int) {
            sizeOfFiles += size
        }

        val size: Int
            get() = sizeOfFiles + children.values.sumOf { it.size }

        fun addChild(name: String): Node = children.getOrPut(name) { Node(name) }

        fun findNodesByCriteria(filter: (Node) -> Boolean): List<Node> =
            children.values.filter(filter) + children.values.flatMap { it.findNodesByCriteria(filter) }
    }

    fun buildTree(input: List<String>): Node {
        val stack = ArrayDeque<Node>()
        input.forEach { line ->
            when {
                line=="$ ls" -> {}
                line.startsWith("dir") -> {}
                line.startsWith("$ cd") -> {
                    val (_, _, arg) = line.split(" ")
                    when (arg) {
                        "/" -> stack.addFirst(Node("/"))
                        ".." -> stack.removeFirst()
                        else -> stack.addFirst(stack.first().addChild(arg))
                    }
                }
                else -> {
                    val (size) = line.split(" ")
                    stack.first().addFile(size.toInt())
                }
            }
        }
        return stack.last()
    }

    fun part1(input: List<String>): Int {
        return buildTree(input)
            .findNodesByCriteria { it.size <= 100000 }
            .sumOf { it.size }
    }

    fun part2(input: List<String>): Int {
        val totalSpace = 70000000
        val requiredSpace = 30000000
        val tree = buildTree(input)
        val unusedSpace = totalSpace - tree.size
        val spaceNeeded = requiredSpace - unusedSpace
        return tree
            .findNodesByCriteria { it.size >= spaceNeeded }
            .minBy { it.size }
            .size
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput)==95437)
    check(part2(testInput)==24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
