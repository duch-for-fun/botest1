package com.lx.bot

import com.lx.botest1.Test1bot
import com.lx.botest1.VERSION
import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.meta.TelegramBotsApi

fun main() {
    ApiContextInitializer.init()
    TelegramBotsApi().registerBot(Test1bot())
    println("ready v$VERSION")
}