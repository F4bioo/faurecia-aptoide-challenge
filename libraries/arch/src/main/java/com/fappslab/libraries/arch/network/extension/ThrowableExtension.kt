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

private fun HttpException.getCause(errorBody: String?): Throwable {
    return getRemoteThrowable(errorBody).errors?.firstOrNull()?.description?.let {
        HttpThrowable(message = it, throwable = this)
    } ?: HttpThrowable(message = UNEXPECTED_ERROR_MESSAGE, throwable = this)
}

private fun getRemoteThrowable(errorBody: String?): RemoteThrowable {
    return Gson().fromJson(errorBody, RemoteThrowable::class.java)
}

internal fun HttpException.parseError(): Throwable {
    return runCatching {
        getCause(errorBody = response()?.errorBody()?.string())
    }.getOrElse { HttpThrowable(message = UNEXPECTED_ERROR_MESSAGE, throwable = this) }
}

internal fun Throwable.toThrowable(): Throwable = when (this) {
    is HttpException -> parseError()
    is UnknownHostException,
    is TimeoutException,
    is InterruptedIOException,
    is SocketException -> DefaultThrowable()
    else -> this
}
