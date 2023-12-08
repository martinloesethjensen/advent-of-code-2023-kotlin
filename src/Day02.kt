fun main() {
    fun part1(input: List<String>, condition: Map<String, Int>): Int {
        return input.toMapOfIdAndColors().filter {
            val cRed = condition["red"] ?: 0
            val cGreen = condition["green"] ?: 0
            val cBlue = condition["blue"] ?: 0

            if ((it.second["red"]?.max() ?: 0) > cRed) return@filter false
            if ((it.second["green"]?.max() ?: 0) > cGreen) return@filter false
            if ((it.second["blue"]?.max() ?: 0) > cBlue) return@filter false

            true
        }.sumOf { it.first.toInt() }
    }

    fun part2(input: List<String>): Int {
        return input.toMapOfIdAndColors().sumOf {
            it.second.map { it.value.max() }.reduce(Int::times)
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input, mapOf("red" to 12, "green" to 13, "blue" to 14)).println()
    part2(input).println()
}

fun List<String>.toMapOfIdAndColors() = map { line ->
    val (id, colorsPart) = line.split(":").let { it.first().split(" ").last() to it.last() }
    val colors = colorsPart
            .split(";")
            .asSequence()
            .flatMap { it.split(",") }
            .map { it.trim().split(" ").zipWithNext() }
            .flatten()
            .groupBy { it.second }
            .mapValues { (_, values) -> values.map { it.first.toInt() } }
    id to colors
}
