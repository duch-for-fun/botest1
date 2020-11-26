package com.lx.bot

import kotlin.random.Random

fun any(vararg words: String) = words[Random.nextInt(words.size)]
fun any(words: List<String>) = words[Random.nextInt(words.size)]
