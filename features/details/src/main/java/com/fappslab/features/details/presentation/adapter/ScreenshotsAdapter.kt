package com.fappslab.features.details.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

internal class ScreenshotsAdapter : ListAdapter<String, ScreenshotsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenshotsViewHolder =
        ScreenshotsViewHolder.create(parent)

    override fun onBindViewHolder(holder: ScreenshotsViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }
}
