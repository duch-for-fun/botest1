package com.lx.bot

import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.User
import java.util.concurrent.TimeUnit
import kotlin.random.Random
import kotlin.system.exitProcess

abstract class BaseLexDialogBot: BaseLexTechBot() {

    abstract fun process(cmd: String, params: String?, user: User, update: Update): String?

    override fun onUpdateReceived(update: Update?) {
        println("\nbase << $update")
        if (update != null) {
            val input = update.message.text
            println("base << $input")
            if (input.startsWith("/")) {
                letsgo(input, update)
            }
        }
    }

    private fun letsgo(input: String, update: Update): String {
        val output = parseAndProcess(input, update)
        send(update.message.chatId, output)
        audit(update, output)
        if (Resp.END == output) exitProcess(1)
        return output
    }

    private fun parseAndProcess(input: String, update: Update): String {
        try {
            val user = update.message.from
            //preprocessing
            val preprocess = preprocess(input, user)
            if (preprocess != null) return preprocess
            //parsing  "/command some params"
            val firstSpace = input.indexOf(" ")
            var cmd = if (firstSpace != -1) input.substring(1, firstSpace) else input.substring(1)
            if (cmd.endsWith("@${getBotUsername()}")) { // "/get@bot"
                cmd = cmd.substring(0, cmd.length-1-getBotUsername().length)
            }
            val params = if (firstSpace != -1) input.substring(firstSpace+1).trim() else null
            println("base << cmd = '$cmd' params = '$params'")
            val resp = process(cmd, params, user, update)
            return postprocess(resp, user)
        } catch (e: Exception) {
            e.printStackTrace()
            return "Ошибка:\n${e.localizedMessage}"
        }
    }

    open fun preprocess(input: String, user: User): String? {
        return null
    }

    open fun postprocess(output: String?, user: User): String {
        if (output == null) return Resp.NOTHING
        if (output.startsWith("debug") || !output.contains("[")) {
            return output
        }
        var rez = output.replace("[user]", getUserName(user))
        if (rez.contains("[fullName]")) {
            rez = rez.replace("[fullName]", "${user.firstName} ${user.lastName ?: ""}")
        }
        return rez
    }

    private fun getUserName(user: User) = user.firstName
}