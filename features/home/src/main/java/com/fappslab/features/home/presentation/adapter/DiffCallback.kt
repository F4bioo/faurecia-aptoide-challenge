package com.fappslab.features.home.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.fappslab.features.home.domain.model.App

internal typealias OnItemClicked = (App) -> Unit

internal class DiffCallback : DiffUtil.ItemCallback<App>() {

    override fun areItemsTheSame(oldItem: App, newItem: App): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: App, newItem: App): Boolean = oldItem.id == newItem.id
}
