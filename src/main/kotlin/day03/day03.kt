package day03

import util.getFileLines

fun main() {

    val linesOfCode = getFileLines("day03/input.txt").lines().filter { it.isNotEmpty() }

    linesOfCode.forEach { println(it) }

    val regex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()

    // Part 1: Find the total of actual `mul(X,Y)` instructions.
    val total = linesOfCode
        .map {
            // Find all `mul(X,Y)` and then generate a List<Pair(X,Y)>
            regex.findAll(it)
                .map { result ->
                    result.groupValues[1].toInt() to result.groupValues[2].toInt() // Pair(X,Y)
                }
                .toList()
        }
        .sumOf { // Sum of totals from all lines
            it.sumOf { p -> p.first * p.second } // Sum of `Pair(X,Y)` from a single line
        }

    println("Total: $total")
}