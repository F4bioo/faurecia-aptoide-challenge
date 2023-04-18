package com.fappslab.libraries.arch.network.downloader

interface Downloader {
    fun downloadApk(apkUrl: String, apkName: String) : Long
}
