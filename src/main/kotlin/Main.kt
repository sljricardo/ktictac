package com.sljricardo

fun main() {
    val game = Game()

    game.draw()

    while (!game.gameover) {
        game.play()
        game.draw()
    }

    println("""
        the winner is: ${game.winner}
    """.trimIndent())
}