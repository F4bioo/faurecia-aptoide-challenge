package com.fappslab.libraries.arch.network.extension

import com.fappslab.libraries.arch.network.exception.DefaultThrowable
import com.fappslab.libraries.arch.network.exception.HttpThrowable
import com.fappslab.libraries.arch.network.exception.RemoteThrowable
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.SocketException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

private const val UNEXPECTED_ERROR_MESSAGE = "Unexpected error, please try again."

internal fun HttpException.parseError(): Throwable = runCatching {
    Gson().fromJson(response()?.errorBody()?.string(), RemoteThrowable::class.java)
}.getOrNull()?.errors?.firstOrNull()?.description?.let {
    HttpThrowable(message = it, throwable = this)
} ?: HttpThrowable(message = UNEXPECTED_ERROR_MESSAGE, throwable = this)

internal fun Throwable.toThrowable(): Throwable = when (this) {
    is HttpException -> parseError()
    is UnknownHostException,
    is TimeoutException,
    is InterruptedIOException,
    is SocketException -> DefaultThrowable()
    else -> this
}
