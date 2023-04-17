package com.fappslab.features.home.presentation.adapter.tops

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.fappslab.features.home.domain.model.App
import com.fappslab.features.home.presentation.adapter.DiffCallback
import com.fappslab.features.home.presentation.adapter.OnItemClicked

internal class TopsAdapter(
    private val itemClicked: OnItemClicked
) : ListAdapter<App, TopsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopsViewHolder =
        TopsViewHolder.create(parent, itemClicked)

    override fun onBindViewHolder(holder: TopsViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }
}
