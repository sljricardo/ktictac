package com.sljricardo

import com.sljricardo.utils.withMockIO
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.ByteArrayInputStream

class GameTest {
    @Test
    fun `test draw output board`() {
        val game = Game()

        val stdout = withMockIO {
            game.draw(noStyle = true)
        }

        val expectedOutput = """
                [1][2][3]
                [4][5][6]
                [7][8][9]
            """.trimIndent()

        assertEquals(expectedOutput, stdout)
    }

    @Test
    fun `test being able to play on turn`() {
        val game = Game()

        val stdout = withMockIO("5\n") {
            game.play()
            game.draw(noStyle = true)
        }

        val expectedOutput = """
                Player (x) turn:
                [1][2][3]
                [4][X][6]
                [7][8][9]
            """.trimIndent()

        assertEquals(expectedOutput, stdout)
    }
}