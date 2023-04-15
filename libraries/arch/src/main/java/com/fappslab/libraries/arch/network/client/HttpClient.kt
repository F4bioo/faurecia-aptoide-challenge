package com.fappslab.libraries.arch.network.client

interface HttpClient {
    fun <T> create(clazz: Class<T>): T
}
