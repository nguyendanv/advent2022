import kotlin.math.abs

fun main() {

    fun follow(moved: Int, follower: Int, positions: List<IntArray>) {
        val touching = abs(positions[moved][0] - positions[follower][0]) <= 1
                && abs(positions[moved][1] - positions[follower][1]) <= 1
        when {
            touching -> {}
            else -> {
                when {
                    positions[moved][0] > positions[follower][0] -> positions[follower][0]++
                    positions[moved][0] < positions[follower][0] -> positions[follower][0]--
                }
                when {
                    positions[moved][1] > positions[follower][1] -> positions[follower][1]++
                    positions[moved][1] < positions[follower][1] -> positions[follower][1]--
                }
            }
        }
    }

    fun solve(input: List<String>, length: Int): Int {
        val positions = (0 until length).map { intArrayOf(0, 0) }
        return input
            .map { it.split(" ") }
            .map { (direction, numSteps) -> direction to numSteps.toInt() }
            .flatMap { (direction, numSteps) ->
                (0 until numSteps).map {
                    when (direction) {
                        "L" -> positions.first()[0]--
                        "R" -> positions.first()[0]++
                        "U" -> positions.first()[1]++
                        "D" -> positions.first()[1]--
                    }
                    positions.indices.windowed(2)
                        .forEach { (moved, follower) -> follow(moved, follower, positions) }
                    positions.last()[0] to positions.last()[1]
                }
            }
            .toSet()
            .count()
    }

    fun part1(input: List<String>): Int {
        return solve(input, 2)
    }

    fun part2(input: List<String>): Int {
        return solve(input, 10)
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput)==13)
    check(part2(testInput)==1)

    val testInput2 = readInput("Day09_test2")
    check(part2(testInput2)==36)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
