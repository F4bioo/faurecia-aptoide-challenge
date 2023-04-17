package com.fappslab.libraries.arch.extension

import com.fappslab.libraries.arch.network.extension.toThrowable
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.parseHttpError(): Single<T> {
    return onErrorResumeNext { throwable ->
        Single.error(throwable.toThrowable())
    }
}

fun Completable.parseHttpError(): Completable {
    return onErrorResumeNext { throwable ->
        Completable.error(throwable.toThrowable())
    }
}

fun <T> Single<T>.applyIoToUiSchedulers(): Single<T> {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Completable.applyIoToUiSchedulers(): Completable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
