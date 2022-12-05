fun main() {
    fun toCrateStacks(input: List<String>, splitIdx: Int): List<ArrayDeque<Char>> {
        val crates = input.subList(0, splitIdx - 1)
            .map { (1 until it.length step 4).toList().map { i -> it[i] } }
        val numStacks = crates.maxOfOrNull { it.size }!!
        return (0 until numStacks)
            .map { i -> ArrayDeque(crates.map { it.getOrElse(i) { ' ' } }.filter { it!=' ' }) }
    }

    fun toInstructions(input: List<String>, splitIdx: Int): List<List<Int>> {
        return input.subList(splitIdx + 1, input.size)
            .map { instr ->
                instr.split("move ", " from ", " to ")
                    .filter { it.isNotBlank() }
                    .map { it.toInt() }
            }
    }

    fun crateMover9000(crateStacks: List<ArrayDeque<Char>>, instructions: List<List<Int>>): String {
        instructions.forEach { (numToMove, from, to) ->
            val toStack = crateStacks[to - 1]
            val fromStack = crateStacks[from - 1]
            repeat(numToMove) { if (fromStack.isNotEmpty()) toStack.addFirst(fromStack.removeFirst()) }
        }
        return crateStacks.map { it.first() }.joinToString("")
    }

    fun crateMover9001(crateStacks: List<ArrayDeque<Char>>, instructions: List<List<Int>>): String {
        instructions.forEach { (numToMove, from, to) ->
            val toStack = crateStacks[to - 1]
            val fromStack = crateStacks[from - 1]
            val tmpStack = ArrayDeque<Char>()
            repeat(numToMove) { if (fromStack.isNotEmpty()) tmpStack.addFirst(fromStack.removeFirst()) }
            while (tmpStack.isNotEmpty()) toStack.addFirst(tmpStack.removeFirst())
        }
        return crateStacks.map { it.first() }.joinToString("")
    }

    fun part1(input: List<String>): String {
        val splitIdx = input.indices.find { i -> input[i].isBlank() }!!
        val crateStacks = toCrateStacks(input, splitIdx)
        val instructions = toInstructions(input, splitIdx)
        return crateMover9000(crateStacks, instructions)
    }

    fun part2(input: List<String>): String {
        val splitIdx = input.indices.find { i -> input[i].isBlank() }!!
        val crateStacks = toCrateStacks(input, splitIdx)
        val instructions = toInstructions(input, splitIdx)
        return crateMover9001(crateStacks, instructions)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput)=="CMZ")
    check(part2(testInput)=="MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
