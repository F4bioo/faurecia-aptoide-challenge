package com.fappslab.features.home.presentation.adapter.editors

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.fappslab.features.home.domain.model.App
import com.fappslab.features.home.presentation.adapter.DiffCallback
import com.fappslab.features.home.presentation.adapter.OnItemClicked

internal class EditorsAdapter(
    private val itemClicked: OnItemClicked
) : ListAdapter<App, EditorsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditorsViewHolder =
        EditorsViewHolder.create(parent, itemClicked)

    override fun onBindViewHolder(holder: EditorsViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }
}
