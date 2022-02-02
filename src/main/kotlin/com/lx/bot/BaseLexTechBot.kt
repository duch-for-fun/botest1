package com.lx.bot

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

abstract class BaseLexTechBot: TelegramLongPollingBot() {

    fun send(chatId: String, text: String) {
        try {
            execute(SendMessage().setChatId(chatId).setText(text))
            println("base to $chatId >> $text")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun send(chatId: Int, text: String) = send(chatId.toString(), text)

    fun send(chatId: Long, text: String) = send(chatId.toString(), text)
}