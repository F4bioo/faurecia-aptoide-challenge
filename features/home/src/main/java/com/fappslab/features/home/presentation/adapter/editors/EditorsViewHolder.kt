package com.fappslab.features.home.presentation.adapter.editors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fappslab.aptoide.features.home.databinding.HomeLargeCoverItemBinding
import com.fappslab.features.home.domain.model.App
import com.fappslab.features.home.presentation.adapter.OnItemClicked
import com.fappslab.features.home.presentation.extension.params
import com.fappslab.libraries.arch.extension.capitalizeFirstChar
import com.fappslab.libraries.arch.extension.load

internal class EditorsViewHolder(
    private val binding: HomeLargeCoverItemBinding,
    private val itemClicked: OnItemClicked
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(app: App) = binding.run {
        imageAvatar.load(app.graphic.params())
        textName.text = app.name.capitalizeFirstChar()
        textStore.text = app.storeName.capitalizeFirstChar()
        cardAvatar.setOnClickListener { itemClicked(app) }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            itemClicked: OnItemClicked
        ): EditorsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = HomeLargeCoverItemBinding
                .inflate(inflater, parent, false)

            return EditorsViewHolder(binding, itemClicked)
        }
    }
}
