package com.fappslab.libraries.arch.extension

fun String.capitalizeFirstChar(): String =
    replaceFirstChar { it.uppercase() }
