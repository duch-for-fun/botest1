package com.lx.bot

import java.io.File
import java.time.LocalDateTime

val history = File("history.txt")

fun history(userId: Int, text: String) {
    history.appendText("\n${LocalDateTime.now()} $userId >> $text")
    println("hist + $text")
}
