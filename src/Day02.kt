fun main() {
    val beatsMap = mapOf(
        1 to 3,
        2 to 1,
        3 to 2
    )

    val losesMap = beatsMap
        .map { (k,v) -> v to k }
        .toMap()

    val pointValues = mapOf(
        "A" to 1,
        "B" to 2,
        "C" to 3,
        "X" to 1,
        "Y" to 2,
        "Z" to 3
    )

    val resultMap = mapOf(
        "X" to 0,
        "Y" to 3,
        "Z" to 6
    )

    fun part1(input: List<String>): Int {
        return input
            .map { it.split(" ") }
            .map { listOf(pointValues[it[0]]!!, pointValues[it[1]]!!) }
            .sumOf { (elfShape, myShape) ->
                myShape + when (elfShape) {
                    myShape -> 3
                    beatsMap[myShape]!! -> 6
                    else -> 0
                }
            }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { it.split(" ") }
            .map { listOf(pointValues[it[0]]!!, resultMap[it[1]]!!) }
            .sumOf { (elfShape, result) ->
                result + when (result) {
                    3 -> elfShape
                    0 -> beatsMap[elfShape]!!
                    else -> losesMap[elfShape]!!
                }
            }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput)==15)
    check(part2(testInput)==12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}