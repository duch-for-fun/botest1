package com.lx.bot

import com.lx.bot.Resp.About.*

data class Resp(val text: String, val about: About = SURE) {

    enum class About {
        TEXT,   //уверен, только текст или инфа от set
        SURE,   //уверен, 1 ссылка на надежный источник
        MORE,   //менее уверен, несколько ссылок
        LIKELY, //не уверен в ответе
        JOKE,   //шутка
        ERROR   //ошибка в разборе запроса
    }

    companion object {
        const val NOTHING = "¯\\_(ツ)_/¯"
        const val CENSORSHIP = "🤬"
        const val ACCESS_DENIED = "введи пароль"
        const val END = "."
    }
}
