package Day03

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val regex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
        return input.flatMap {
            val matches = regex.findAll(it).toList()
            matches.map {

                it.destructured.let {(value1, value2) -> value1.toInt() * value2.toInt()}}
        }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.joinToString().let { inputString ->
            val mulRegex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
            val instructionRegex = """do(?:n't)?\(\)""".toRegex()

            val mulMatches = mulRegex.findAll(inputString).toList()
            val instructionMatches = instructionRegex.findAll(inputString).toList()

            mulMatches.map { match ->
                val instructionsBefore = instructionMatches.filter { curr -> curr.range.first < match.range.first }
                val enabled = instructionsBefore.lastOrNull()?.let {
                    it.value == "do()"
                } ?: true

                if(enabled)
                    match.destructured.let {(value1, value2) -> value1.toInt() * value2.toInt()}
                else
                    0
            }
        }.sum()
    }

    val input = readInput("Day03/Day03_input")
    val testInput = readInput("Day03/Day03_test")

    check(part1(testInput) == 161)
    check(part2(testInput) == 48)

    part1(input).println()
    part2(input).println()
}