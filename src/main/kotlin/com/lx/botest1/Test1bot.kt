package com.lx.botest1

import com.lx.bot.BaseLexDialogBot
import com.lx.bot.Resp
import com.lx.bot.any
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
            "hi"     -> hi()
            "whoami" -> "[user], ты $user"
            "get"    -> Resp.ACCESS_DENIED
            "debug"  -> debug()
            "end"    -> Resp.END
            else     -> null
        }

    fun welcome(user: User) = "Ну наконец-то, ${user.firstName} ${user.lastName ?: ""} 👋 \nначни с 👉 /help"

    fun help() = "${just()}:\n" +
            "/start\n" +
            "/help\n" +
            "/whoami\n" +
            "/hi 👽"

    fun just() = any("тебе пока доступны команды","на текущий момент тебе доступны команды","тебе открыты несколько команд","тебе доступно несколько команд","тебе пока открыты только")

    fun hi() = when (LocalTime.now().hour) {
            in 6..11  -> morning()
            in 12..17 -> goodDay()
            in 18..23 -> goodEvening()
            else      -> goodNight()
        }

    fun morning() = any("Доброе утро", "Доброго утра 🤗", "Утра доброго 🌅", "Ну и утречко ☕️", "Привет, солнце то уже высоко 🌇", "С бодрым утром 🌅", "Может кофе ☕", "[user], маску не забудь..️ 🦠")
    fun goodDay() = any("Добрый день", "Приветствую, [user] ✌️", "[user], привет 👋", "Хорошего дня, [user]! 👌", "[user], xорошего дня ✌️", "Ну и денек, [user] 🤯",  "Привет, солнце еще высоко 🌆", "[user], как самочувствие?️ 😷", "Успешного дня, [user] 💪", "[user], маску не забыл..️ 🦠")
    fun goodEvening() = any("Добрый вечер", "Добрый вечер, [user] ✌️", "[user], хорошего вечера 👋", "Хорошего вечера, [user]! 👌", "Хорошего вечера 👍", "Ну и денек, [user] 🤯",  "Привет, солнце то уже давно село 🌆", "[user], как самочувствие?️ 😷", "[user], маску сегодня не забыл..️ 🦠")
    fun goodNight() = any("Хорошей ночи", "Привет ✌️", "Привет, [user] ✌️", "[user], привет 🤗", "Ну и денек, [user] 🤯", "Ну и денек, [user] 💤", "Ну и денек, [user] ✌️", "[user], как самочувствие?️ 😷", "Спать не пора, [user] 🌆", "Завтра, [user], не забудь маску..️ 🦠")

    private val censorList = listOf("гавно","говно","говняный","дерьмо","дерьмовый","жопа","хуй","хуевый","писька","пизда","fuck")

    override fun preprocess(input: String, user: User): String? {
        censorList.forEach {
            if (input.contains(it)) {
                alarm(user,"fucking req(\"$input\")")
                return Resp.CENSORSHIP
            }
        }
        return null
    }

    fun debug() = "debug v$VERSION"
}