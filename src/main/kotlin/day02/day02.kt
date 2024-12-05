package day02

import util.getFileLines

fun main() {

    val linesOfNums = getFileLines("day02/input.txt")
        .lines()
        .map { line ->
            line.split(" ")
                .filter { it.isNotBlank() }
                .map { it.toInt() }
        }
        .filterNot { it.isEmpty() }

    // Part 1: find the total number of "safe" levels.
    val safeList = linesOfNums
        .map { it.zipWithNext { a, b -> b - a } } // Find the differences between each consecutive elements.
        .filter {

            it[0] > 0 && it.all { d -> d in 1..3 }    // All positives within range 1..3
                    || it[0] < 0 && it.all { d -> d in -3..-1 } // or All negatives within range -3..-1

        }

    println("Total number of safe list: ${safeList.count()}")

}