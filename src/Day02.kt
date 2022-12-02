fun main() {
    val beatsMap = mapOf(
        1 to 3,
        2 to 1,
        3 to 2
    )
    val pointValues = mapOf(
        "A" to 1,
        "B" to 2,
        "C" to 3,
        "X" to 1,
        "Y" to 2,
        "Z" to 3
    )

    fun solution(input: List<String>): Int {
        return input
            .map { it.split(" ") }
            .map { listOf(pointValues[it[0]]!!, pointValues[it[1]]!!) }
            .sumOf { (elfShape, myShape) ->
                myShape + (if (myShape==elfShape) 3
                else if (beatsMap[myShape]==elfShape) 6
                else 0)
            }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(solution(testInput)==15)

    val input = readInput("Day02")
    println(solution(input))
}