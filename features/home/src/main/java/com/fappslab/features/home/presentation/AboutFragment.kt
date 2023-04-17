package com.fappslab.features.home.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.fappslab.aptoide.features.home.R
import com.fappslab.aptoide.libraries.design.databinding.LayoutDsmodalBinding
import com.fappslab.features.home.presentation.viewmodel.HomeViewModel
import com.fappslab.libraries.arch.viewbinding.viewBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.fappslab.aptoide.libraries.design.R as DS

internal class AboutFragment : Fragment(DS.layout.layout_dsmodal) {

    private val binding: LayoutDsmodalBinding by viewBinding()
    private val viewModel: HomeViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() = binding.run {
        imageAvatar.isVisible = true
        textTile.isVisible = true
        textMessage.isVisible = true
        buttonPrimary.isVisible = true

        imageAvatar.setImageResource(DS.drawable.ds_illu_challenge)
        textTile.text = getString(R.string.home_about_title)
        textMessage.text = getString(R.string.home_about_Message)

        buttonPrimary.text = getString(DS.string.common_got_it)
        buttonPrimary.setOnClickListener { viewModel.onAboutDismiss() }
    }

    companion object {
        fun create(): AboutFragment = AboutFragment()
    }
}
