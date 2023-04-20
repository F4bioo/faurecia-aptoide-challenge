package com.fappslab.features.home.presentation.adapter.trending

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.fappslab.features.home.domain.model.App
import com.fappslab.features.home.presentation.adapter.DiffCallback
import com.fappslab.features.home.presentation.adapter.OnItemClicked

internal class TrendingAdapter(
    private val itemClicked: OnItemClicked
) : ListAdapter<App, TrendingViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder =
        TrendingViewHolder.create(parent, itemClicked)

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }
}
