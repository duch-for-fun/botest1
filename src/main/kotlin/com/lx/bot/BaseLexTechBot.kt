package com.lx.bot

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.User
import java.util.concurrent.TimeUnit

const val ROOT_ID = 5384894

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

    fun sendToRoot(text: String) = send(ROOT_ID, text)

    fun alarm(user: User, alarm: String) {
        println("\nALARM !!!")
        sendToRoot("$alarm\nfrom $user")
        history(user.id, alarm)
    }

    fun audit(update: Update, resp: String) {
        if (update.message.from.id != ROOT_ID) {
            sendToRoot("${update.message.from.id}: ${update.message.text}\n>> ${resp}")
        }
    }
}