package com.fappslab.features.details.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fappslab.aptoide.features.details.databinding.DetailsSmallCoverItemBinding
import com.fappslab.libraries.design.extension.loadImage

internal class ScreenshotsViewHolder(
    private val binding: DetailsSmallCoverItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(imageUrl: String) {
        binding.imageAvatar.loadImage(imageUrl)
    }

    companion object {
        fun create(parent: ViewGroup): ScreenshotsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DetailsSmallCoverItemBinding
                .inflate(inflater, parent, false)

            return ScreenshotsViewHolder(binding)
        }
    }
}
