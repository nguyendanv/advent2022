fun main() {
    fun part1(input: List<String>): Int {
        return input
            .filter { pair ->
                val numbers = pair.split(",", "-").map { it.toInt() }
                val first = (numbers[0]..numbers[1]).toSet()
                val second = (numbers[2]..numbers[3]).toSet()
                first.containsAll(second) || second.containsAll(first)
            }
            .size
    }

    fun part2(input: List<String>): Int {
        return input
            .filter { pair ->
                val numbers = pair.split(",", "-").map { it.toInt() }
                val first = (numbers[0]..numbers[1]).toSet()
                val second = (numbers[2]..numbers[3]).toSet()
                first.intersect(second).isNotEmpty()
            }
            .size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput)==2)
    check(part2(testInput)==4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
