package com.sljricardo

class Game {
    var gameover = false
    var winner = ""
    private var turn = 'X'
    private var plays = emptyMap<Int, Char>().toMutableMap()
    private var board = mutableMapOf(
        1 to Pair(0,0), 2 to Pair(0,1), 3 to Pair(0,2),
        4 to Pair(1,0), 5 to Pair(1,1), 6 to Pair(1,2),
        7 to Pair(2,0), 8 to Pair(2,1), 9 to Pair(2,2)
    )

    fun draw() {
        for (coord in board) {
            val cell = if (plays.containsKey(coord.key)) {
                "${plays[coord.key]}"
            } else {
                "${AnsiColor.DARK_GRAY.code}${coord.key}${AnsiColor.RESET.code}"
            }
            print("""
                [$cell]
            """.trimIndent())
            if (coord.key%3 == 0) println()
        }
    }

    fun play() {
        while (true) {
            println("Player (${turn.lowercaseChar()}) turn: ")
            val coord = readln().toInt()

            if (isValidPlay(coord)) {
                plays[coord] = turn
                turn = if (turn == 'X') 'O' else 'X'
                checkWinner()
                return;
            }
            println("Invalid play, please try again")
        }
    }

    private fun isValidPlay(coord: Int) = plays[coord] == null && coord in 1..9

    private fun checkWinner() {
        // Check rows
        for (row in 1..9 step 3) {
            if (plays.containsKey(row) && plays[row] == plays[row + 1] && plays[row + 1] == plays[row + 2]) {
                gameover = true
                winner = plays[row].toString()
                return
            }
        }

        // Check coll
        for (col in 1..3) {
            if (plays.containsKey(col) && plays[col] == plays[col + 3] && plays[col + 3] == plays[col + 6]) {
                gameover = true
                winner = plays[col].toString()
                return
            }
        }

        // check diagonal
        val diagonals = listOf(
            listOf(1, 5, 9), // First diagonal
            listOf(3, 5, 7)  // Second diagonal
        )

        for (diagonal in diagonals) {
            if (diagonal.all { plays[it] == 'X' || plays[it] == 'O' }) {
                gameover = true
                winner = plays[diagonal.first()].toString() // Set winner based on the first position of the diagonal
                return
            }
        }
    }
}