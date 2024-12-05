package day02

import util.getFileLines

private fun differences(list: List<Int>) = list.zipWithNext { a, b -> b - a }

private fun isDiffsSafe(differences: List<Int>) =
    differences[0] > 0 && differences.all { d -> d in 1..3 }    // All positives within range 1..3
            || differences[0] < 0 && differences.all { d -> d in -3..-1 } // or All negatives within range -3..-1

private fun isLevelsSafe(levels: List<Int>) = isDiffsSafe(differences(levels))

// Create a list with one item dropped in each iteration: [1,2,3,4] -> [2,3,4], [1,3,4], [1,2,4], [1,2,3]
private fun <T> oneItemDropped(list: List<T>) = List(list.size) { index ->
    list.filterIndexed { i, _ -> i != index }
}


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
        .filter { isLevelsSafe(it) }

    println("Total number of safe list: ${safeList.count()}")


    // Part 2: Find the lists that can be safe by removing just one item.
    val safeListWithTolerance = linesOfNums
        .filter { !isLevelsSafe(it) } // Pick the unsafe items
        .filter {
            oneItemDropped(it) // Make a list with 1 item dropped in each iteration.
                .any { dropped -> isLevelsSafe(dropped) } // Find if any of the items in that list is safe.
            // There might be a better way than creating N lists and looping through each of them.
        }

    println("Total number of safe list with tolerance: ${safeList.count() + safeListWithTolerance.count()}")

}