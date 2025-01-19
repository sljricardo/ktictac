package com.sljricardo.utils

import java.io.ByteArrayInputStream

fun withMockIO(
    stdin: String = "",
    block: () -> Unit
): String {
    // Mock stdin
    System.setIn(ByteArrayInputStream(stdin.toByteArray()))

    // Mock stdout
    val outputStream = java.io.ByteArrayOutputStream()
    // Redirect System.out to stream to assert later
    System.setOut(java.io.PrintStream(outputStream))

    return try {
        block()
        // Convert the captured output to a string
        outputStream.toString().trim().replace("\r\n", "\n")
    } finally {
        // Restore original System.out and System.`in`
        System.setOut(System.out)
        System.setIn(System.`in`)
    }
}