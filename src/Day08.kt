fun main() {

    fun part1(input: List<String>): Int {
        val rows = input.map { it.map(Char::digitToInt) }
        val columns = rows.first().indices.map { i -> rows.map { it[i] } }
        return rows.flatMapIndexed { i, row ->
            row.mapIndexed { j, height ->
                val isTaller: (Int) -> Boolean = { height > it }
                val westVisible = row.take(j).all(isTaller)
                val eastVisible = row.drop(j + 1).all(isTaller)
                val northVisible = columns[j].take(i).all(isTaller)
                val southVisible = columns[j].drop(i + 1).all(isTaller)
                westVisible || eastVisible || northVisible || southVisible
            }
        }.count { it }
    }

    fun part2(input: List<String>): Int {
        val rows = input.map { it.map(Char::digitToInt) }
        val columns = rows.first().indices.map { i -> rows.map { it[i] } }
        return rows.flatMapIndexed { i, row ->
            row.mapIndexed { j, height ->
                val isTaller: (Int) -> Boolean = { height > it }
                val westScore = row.take(j).takeLastWhileInclusive(isTaller).count()
                val eastScore = row.drop(j + 1).takeWhileInclusive(isTaller).count()
                val northScore = columns[j].take(i).takeLastWhileInclusive(isTaller).count()
                val southScore = columns[j].drop(i + 1).takeWhileInclusive(isTaller).count()
                westScore * eastScore * northScore * southScore
            }
        }.max()
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput)==21)
    check(part2(testInput)==8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}

fun <T> List<T>.takeWhileInclusive(last: Boolean = false, predicate: (T) -> Boolean): List<T> {
    var shouldContinue = true
    return (if (last) ::takeLastWhile else ::takeWhile) {
        val result = shouldContinue
        shouldContinue = predicate(it)
        result
    }
}

fun <T> List<T>.takeWhileInclusive(predicate: (T) -> Boolean): List<T> {
    return this.takeWhileInclusive(false, predicate)
}

fun <T> List<T>.takeLastWhileInclusive(predicate: (T) -> Boolean): List<T> {
    return this.takeWhileInclusive(true, predicate)
}