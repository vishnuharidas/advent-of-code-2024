package day03

import util.getFileLines

fun main() {

    fun sumOfMuls(string: String) = """mul\((\d{1,3}),(\d{1,3})\)"""
        .toRegex()
        .findAll(string)
        .map {
            it.groupValues[1].toInt() to it.groupValues[2].toInt() // Pair(X,Y)
        }
        .sumOf { it.first * it.second } // Sum of `Pair(X,Y)` from a single line


    val instructions = getFileLines("day03/input.txt")
        .lines()
        .filter { it.isNotEmpty() }
        .joinToString("")


    // Part 1: Find the total of actual `mul(X,Y)` instructions.
    println("Total 1: ${sumOfMuls(instructions)}")


    // Part 2: Consider `do()` and `don't()` also.
    val total2 = """(^|do\(\)).*?(${'$'}|don't\(\))"""
        .toRegex()
        .findAll(instructions)
        .sumOf { matchResult ->
            sumOfMuls(matchResult.value)
        }

    println("Total 2: $total2")

}