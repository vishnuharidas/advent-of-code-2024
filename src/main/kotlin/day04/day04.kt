package day04

import util.getFileLines

fun main() {

    val matrix = getFileLines("day04/input.txt")
        .lines()
        .filter { it.isNotBlank() }

    fun findXmasCount(r: Int, c: Int, matrix: List<String>): Int {

        fun hasWord(predicate: () -> Boolean) = try {
            predicate()
        } catch (e: Exception) {
            false
        }

        // Vertical
        val n = hasWord { matrix[r - 1][c] == 'M' && matrix[r - 2][c] == 'A' && matrix[r - 3][c] == 'S' }
        val e = hasWord { matrix[r][c + 1] == 'M' && matrix[r][c + 2] == 'A' && matrix[r][c + 3] == 'S' }
        val s = hasWord { matrix[r + 1][c] == 'M' && matrix[r + 2][c] == 'A' && matrix[r + 3][c] == 'S' }
        val w = hasWord { matrix[r][c - 1] == 'M' && matrix[r][c - 2] == 'A' && matrix[r][c - 3] == 'S' }

        // Diagonal
        val ne = hasWord { matrix[r - 1][c + 1] == 'M' && matrix[r - 2][c + 2] == 'A' && matrix[r - 3][c + 3] == 'S' }
        val nw = hasWord { matrix[r - 1][c - 1] == 'M' && matrix[r - 2][c - 2] == 'A' && matrix[r - 3][c - 3] == 'S' }
        val se = hasWord { matrix[r + 1][c + 1] == 'M' && matrix[r + 2][c + 2] == 'A' && matrix[r + 3][c + 3] == 'S' }
        val sw = hasWord { matrix[r + 1][c - 1] == 'M' && matrix[r + 2][c - 2] == 'A' && matrix[r + 3][c - 3] == 'S' }

        return listOf(n, e, s, w, ne, nw, se, sw).count { it }
    }


    var totalXmas = 0
    matrix.forEachIndexed { row, s ->
        s.forEachIndexed { col, c ->
            if (c == 'X') {
                totalXmas += findXmasCount(row, col, matrix)
            }
        }
    }

    println("Total XMAS: $totalXmas")
}