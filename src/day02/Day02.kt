package day02

import Day
import Part
import readInput

class Day02(input: List<String>) : Day {

    private val games: List<Game> = input.map {
        Game.of(it)
    }


    private val partOne = Part {
        games.filter {
            it.isPossible(12, 13, 14)
        }.sumOf { it.id }

    }

    private val partTwo = Part {
        games.sumOf { it.power() }
    }

    private data class Game(
        val id: Int,
        val red: Int,
        val green: Int,
        val blue: Int,
    ) {

        fun isPossible(red: Int, green: Int, blue: Int) =
            this.red <= red && this.green <= green && this.blue <= blue

        fun power() =
            red * blue * green

        companion object {
            fun of(input: String): Game {
                val id = input.substringAfter(" ").substringBefore(":").toInt()
                val colors = mutableMapOf<String, Int>()

                input.substringAfter(":").split(";").forEach { turn ->

                    turn.split(",").map {
                        it.trim()

                    }.forEach { draw ->
                        val drawNum = draw.substringBefore(" ").toInt()
                        val color = draw.substringAfter(" ")

                        colors[color] = maxOf(drawNum, colors[color] ?: drawNum)
                    }
                }
                return Game(id, colors["red"] ?: 0, colors["green"] ?: 0, colors["blue"] ?: 0)
            }
        }
    }

    override fun solution() {
        println(partOne.call())
        println(partTwo.call())
    }
}

fun main() {

    val input = readInput("day02")
    Day02(input).solution()
}