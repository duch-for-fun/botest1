package com.lx.botest1

import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.meta.TelegramBotsApi

fun main() {
    ApiContextInitializer.init()
    TelegramBotsApi().registerBot(Test1bot())
    println("ready v$VERSION")
}