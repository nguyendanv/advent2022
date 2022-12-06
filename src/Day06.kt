fun main() {
    fun part1(input: List<String>): Int {
        val windows = input[0].windowed(4, 1)
        for (i in windows.indices) {
            if (windows[i].toSet().size==4)
                return i+4
        }
        return -1
    }

    fun part2(input: List<String>): Int {
        val windows = input[0].windowed(14, 1)
        for (i in windows.indices) {
            if (windows[i].toSet().size==14)
                return i+14
        }
        return -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day06_test1")
    val testInput2 = readInput("Day06_test2")
    val testInput3 = readInput("Day06_test3")
    val testInput4 = readInput("Day06_test4")
    val testInput5 = readInput("Day06_test5")

    check(part1(testInput1)==7)
    check(part1(testInput2)==5)
    check(part1(testInput3)==6)
    check(part1(testInput4)==10)
    check(part1(testInput5)==11)

    check(part2(testInput1)==19)
    check(part2(testInput2)==23)
    check(part2(testInput3)==23)
    check(part2(testInput4)==29)
    check(part2(testInput5)==26)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
