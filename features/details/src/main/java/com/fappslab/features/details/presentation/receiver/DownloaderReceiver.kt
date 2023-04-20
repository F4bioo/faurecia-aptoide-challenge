package com.fappslab.features.details.presentation.receiver

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.fappslab.aptoide.features.details.R

internal const val DOWNLOAD_COMPLETE = "android.intent.action.DOWNLOAD_COMPLETE"
internal const val DEFAULT_DOWNLOAD_ID = -1L

internal class DownloaderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == DOWNLOAD_COMPLETE) {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, DEFAULT_DOWNLOAD_ID)
            if (id != DEFAULT_DOWNLOAD_ID) showToast(context)
        }
    }

    private fun showToast(context: Context?) {
        val message = context?.getText(R.string.details_download_finished)
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
