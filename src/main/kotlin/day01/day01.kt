package day01

import util.getFileLines
import kotlin.math.abs

fun main(){

    val linesOfNums = getFileLines("day01/input.txt")
        .lines()
        .map { line -> line.split(" ").filter { it.isNotBlank() } }
        .filterNot { it.isEmpty() }

    val leftList = linesOfNums.map { it.first().toInt() }.sorted()    // Sorted left list
    val rightList = linesOfNums.map { it.last().toInt() }.sorted()    // Sorted right list

    // Part 1: find sum of differences
    val sumDiffs = leftList.zip(rightList) // Combined into pairs
        .sumOf { abs(it.first - it.second) }

    println("Part 01: $sumDiffs")

    // Part 2: find sum of "similarity scores"
    val sumOfSimilarityScores = leftList
        .distinct()
        .associateWith { i -> rightList.count { it == i } }
        .map { it.key * it.value }
        .sum()

    println("Part 02: $sumOfSimilarityScores")

}