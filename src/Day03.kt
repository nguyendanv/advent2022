fun main() {
    fun part1(input: List<String>): Int {
        return input
            .flatMap { sack ->
                val priorities = sack.map { if (it in 'a'..'z') it - 0x60 else it - 0x26 }.map { it.code }
                val first = priorities.subList(0, priorities.size / 2)
                val second = priorities.subList(priorities.size / 2, priorities.size)
                first.intersect(second.toSet()).toList()
            }
            .sum()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput)==157)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
