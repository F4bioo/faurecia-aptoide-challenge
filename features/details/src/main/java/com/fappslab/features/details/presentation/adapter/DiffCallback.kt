package com.fappslab.features.details.presentation.adapter

import androidx.recyclerview.widget.DiffUtil

internal class DiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
        areItemsTheSame(oldItem, newItem)
}
