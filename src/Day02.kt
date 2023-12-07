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
        input.toMapOfIdAndColors().println()
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput, condition = mapOf("red" to 12, "green" to 13, "blue" to 14)) == 8)

    val input = readInput("Day02")
    part1(input, mapOf("red" to 12, "green" to 13, "blue" to 14)).println()
    part2(input).println()
}

fun List<String>.toMapOfIdAndColors() = map {
    val id = it.split(":").first().split(" ").last()
    val colors = it
            .split(":").last()
            .split(";")
            .asSequence()
            .map { it.split(",") }
            .flatten()
            .map { it.trim().split(" ").zipWithNext() }
            .flatten()
            .groupBy { it.second }
            .map { it.key to it.value.map { it.first.toInt() } }
            .toMap()
    id to colors
}
