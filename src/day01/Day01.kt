package day01

import Day
import Part
import println
import readInput

fun main() {

    val input = readInput("day01")
    val solution = DayOne(input).solution()
    println(solution.println())

}

class DayOne(private val input: List<String>) : Day {


    private val words = mapOf(
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

    private val partOne = Part {
        input.sumOf { calibrateValue(it) }
    }

    private val partTwo = Part {
        input.sumOf { row ->
            calibrateValue(
                row.mapIndexedNotNull { index, c ->
                    if (c.isDigit()) c
                    else
                        row.possibleWordsAt(index).firstNotNullOfOrNull { candidate ->
                            words[candidate]
                        }
                }.joinToString()
            )
        }
    }

    private fun String.possibleWordsAt(startingAt: Int): List<String> =
        (3..5).map { len ->
            substring(startingAt, (startingAt + len).coerceAtMost(length))
        }

    private fun calibrateValue(row: String) =
        "${row.first { it.isDigit() }}${row.last { it.isDigit() }}".toInt()


    override fun solution() {
        println(partOne.call())
        println(partTwo.call())
    }
}