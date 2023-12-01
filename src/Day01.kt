import java.util.*

fun main() {
    val digits = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
    )

    fun part1(input: List<String>): Int = input
            .map { it.replace(Regex("\\D"), "") }
            .sumOf { "${it.first()}${it.last()}".toInt() }

    fun part2(input: List<String>): Int = input
            .sumOf { item ->
                item.lowercase(Locale.getDefault()).run {
                    digits.filterKeys { contains(it) }
                            .asIterable()
                            .fold(this) { acc, entry ->
                                acc.replace(entry.key, entry.key + entry.value + entry.key)
                            }
                }.replace(Regex("\\D"), "").let { "${it.first()}${it.last()}".toInt() }
            }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
