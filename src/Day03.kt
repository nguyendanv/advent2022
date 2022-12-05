fun main() {
    fun part1(input: List<String>): Int {
        return input
            .flatMap {
                val first = it.subSequence(0, it.length / 2).toSet()
                val second = it.subSequence(it.length / 2, it.length).toSet()
                first.intersect(second).toList()
            }
            .map { if (it in 'a'..'z') it - 0x60 else it - 0x26 }
            .sumOf { it.code }
    }

    fun part2(input: List<String>): Int {
        return input.windowed(3, 3)
            .flatMap { (first, second, third) ->
                first.toSet()
                    .intersect(second.toSet())
                    .intersect(third.toSet())
                    .toList()
            }
            .map { if (it in 'a'..'z') it - 0x60 else it - 0x26 }
            .sumOf { it.code }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput)==157)
    check(part2(testInput)==70)


    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
