package com.fappslab.features.home.presentation.adapter.trending

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fappslab.aptoide.features.home.databinding.HomeSmallCoverItemBinding
import com.fappslab.features.home.domain.model.App
import com.fappslab.features.home.presentation.adapter.OnItemClicked
import com.fappslab.libraries.arch.extension.capitalizeFirstChar
import com.fappslab.libraries.design.extension.loadImage

internal class TrendingViewHolder(
    private val binding: HomeSmallCoverItemBinding,
    private val itemClicked: OnItemClicked
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(app: App) = binding.run {
        imageAvatar.loadImage(app.icon)
        textName.text = app.name.capitalizeFirstChar()
        textStore.text = app.storeName.capitalizeFirstChar()
        cardAvatar.setOnClickListener { itemClicked(app) }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            itemClicked: OnItemClicked
        ): TrendingViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = HomeSmallCoverItemBinding
                .inflate(inflater, parent, false)

            return TrendingViewHolder(binding, itemClicked)
        }
    }
}
