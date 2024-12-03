package Day02

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.map {
            if (it.split(" ").map { it.toInt() }.isSafe())
                1
            else 0
        }.sum()
    }

    fun part2(input: List<String>): Int {
        val mapped = input.map {
            it.split(" ").map { it.toInt() }
        }

        return mapped.map { list ->
            if (list.isSafe())
                1
            else if ((0..list.size).map {
                    val newList = list.filterIndexed { index, _ -> index != it }
                    if (newList.isSafe())
                        1
                    else 0
                }.find { it == 1 } != null)
                1
            else 0
        }.sum()
    }

    val input = readInput("Day02/Day02_input")
    val testInput = readInput("Day02/Day02_test")

    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    part1(input).println()
    part2(input).println()
}

fun List<Int>.isSafe(): Boolean {
    val diff = this.zipWithNext { a, b -> b - a }
    return diff.all { it in 1..3 } || diff.all { it in -3..-1 }
}