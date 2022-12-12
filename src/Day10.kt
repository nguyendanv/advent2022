fun main() {
    fun part1(input: List<String>): Int {
        var cycle = 1
        var x = 1
        return input
            .map { it.split(" ") }
            .flatMap { instr ->
                val cyclesToExecute = when (instr[0]) {
                    "noop" -> 1
                    "addx" -> 2
                    else -> 0
                }
                val arg = when (instr[0]) {
                    "addx" -> instr[1].toInt()
                    else -> 0
                }

                (0 until cyclesToExecute)
                    .map {
                        cycle++
                        if (it==cyclesToExecute - 1) x += arg
                        cycle to x
                    }
            }
            .toMap()
            .filter { (k, v) -> (k - 20) % 40==0 }
            .map { (k, v) -> k * v }
            .sum()
    }

    fun part2(input: List<String>): String {
        var cycle = 0
        var x = 1
        return input
            .map { it.split(" ") }
            .flatMap { instr ->
                val cyclesToExecute = when (instr[0]) {
                    "noop" -> 1
                    "addx" -> 2
                    else -> 0
                }
                val arg = when (instr[0]) {
                    "addx" -> instr[1].toInt()
                    else -> 0
                }

                (0 until cyclesToExecute)
                    .map {
                        val thisCycle = cycle
                        val isLit = (cycle % 40 in x - 1..x + 1)
                        cycle++
                        if (it==cyclesToExecute - 1) x += arg
                        thisCycle to isLit
                    }
            }
            .filter { (k, v) -> k <= 240 }
            .map { (k, v) -> if (v) "#" else "." }
            .joinToString("")
            .windowed(40, 40)
            .joinToString("\n")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput)==13140)
    check(part2(testInput)=="""
        ##..##..##..##..##..##..##..##..##..##..
        ###...###...###...###...###...###...###.
        ####....####....####....####....####....
        #####.....#####.....#####.....#####.....
        ######......######......######......####
        #######.......#######.......#######.....
    """.trimIndent())

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}
