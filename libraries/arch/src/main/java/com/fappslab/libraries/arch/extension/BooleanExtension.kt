package com.fappslab.libraries.arch.extension

fun <T : Any> T?.isNull() = this == null

fun <T : Any> T?.isNotNull() = !isNull()
