package com.fappslab.libraries.arch.network.downloader

import android.app.DownloadManager
import android.os.Environment
import androidx.core.net.toUri

internal class DownloaderImpl(
    private val downloadManager: DownloadManager
) : Downloader {

    override fun downloadApk(apkUrl: String, apkName: String): Long {
        val name = apkName.sanitizeApkName()
        val request = DownloadManager.Request(apkUrl.toUri())
            .setTitle(name)
            .setMimeType("application/vnd.android.package-archive")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name)
        return downloadManager.enqueue(request)
    }

    private fun String.sanitizeApkName() =
        "${replace(oldValue = " ", newValue = "_").lowercase()}.apk"
}
