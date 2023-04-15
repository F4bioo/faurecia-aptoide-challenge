package com.fappslab.libraries.arch.jsonhandle

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.lang.reflect.Type

inline fun <reified DataModel> readFromModelToJSON(model: DataModel?): String =
    Gson().toJson(model)

fun readFromJSONToString(jsonFile: String): String =
    requireNotNull(ClassLoader.getSystemResource(jsonFile)?.readText()) {
        throw FileNotFoundException("File $jsonFile not found!")
    }

inline fun <reified DataModel> readFromJSONToModel(
    jsonFile: String,
    gson: Gson = Gson(),
    encoding: String = "UTF-8",
    type: Type = object : TypeToken<DataModel>() {}.type
): DataModel = jsonFile.toBufferedReader(encoding).use { reader ->
    gson.fromJson(reader, type)
}

fun String.toBufferedReader(encoding: String): BufferedReader = runCatching {
    val classLoader = Thread.currentThread().contextClassLoader
    InputStreamReader(classLoader?.getResourceAsStream(this), encoding).buffered()
}.onFailure {
    throw FileNotFoundException("File $this not found!")
}.getOrThrow()
