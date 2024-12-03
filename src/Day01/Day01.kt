package Day01

import println
import readInput
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val mapped = input.map {
            it.split("   ").map {it.toInt()}
        }

        val firstList = mapped.map { it.first()}.sorted()

        val secondList = mapped.map { it.last()}.sorted()

        return firstList.mapIndexed {index, value -> abs(value - secondList[index]) }.sum()
    }

    fun part2(input: List<String>): Int {
        val mapped = input.map {
            it.split("   ").map {it.toInt()}
        }

        val secondList = mapped.map {
            it[1]
        }

        return mapped.sumOf {
            val value = it[0]

            value * secondList.filter { it == value }.size
        }
    }

    val input = readInput("Day01/Day01_input")
    val testInput = readInput("Day01/Day01_test")

    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    part1(input).println()
    part2(input).println()
}
