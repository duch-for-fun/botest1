package com.lx.botest1

import com.lx.bot.BaseLexDialogBot
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.User
import java.time.LocalTime

const val VERSION = "1.0"

//t.me/test111111111111111111bot
class Test1bot: BaseLexDialogBot() {

    override fun getBotUsername() = "test111111111111111111bot"

    override fun getBotToken() = System.getenv("apiToken") ?: "-"

    override fun process(cmd: String, params: String?, user: User, update: Update) =
        when (cmd) {
            "start"  -> welcome(user)
            "help"   -> help()
            "whoami" -> "[user], ты $user"
            "hi"     -> hi()
            else     -> null
        }

    private fun welcome(user: User) = "Ну наконец-то, ${user.firstName} ${user.lastName ?: ""} 👋 \nначни с 👉 /help"

    private fun help() = """
        на текущий момент тебе доступны команды:
        /start
        /help
        /whoami
        /hi
    """.trimIndent()

    private fun hi() = when (LocalTime.now().hour) {
            in 6..11  -> "Доброго утра 🤗"
            in 12..17 -> "Добрый день ✌️"
            in 18..23 -> "Хорошего вечера 👌"
            else      -> "Спать не пора 🌆"
        }
}