package com.fappslab.features.home.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.fappslab.aptoide.libraries.design.databinding.LayoutDsmodalBinding
import com.fappslab.features.home.presentation.model.ErrorArgs
import com.fappslab.features.home.presentation.viewmodel.HomeViewModel
import com.fappslab.libraries.arch.args.view.putArguments
import com.fappslab.libraries.arch.args.view.viewArgs
import com.fappslab.libraries.arch.args.view.withArgs
import com.fappslab.libraries.arch.extension.capitalizeFirstChar
import com.fappslab.libraries.arch.viewbinding.viewBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.fappslab.aptoide.libraries.design.R as DS

internal class ErrorFragment : Fragment(DS.layout.layout_dsmodal) {

    private val binding: LayoutDsmodalBinding by viewBinding()
    private val viewModel: HomeViewModel by sharedViewModel()
    private val args: ErrorArgs by viewArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() = binding.run {
        imageAvatar.isVisible = true
        textTile.isVisible = true
        textMessage.isVisible = true
        buttonPrimary.isVisible = true

        imageAvatar.setImageResource(DS.drawable.ds_illu_moon)
        textTile.text = getString(DS.string.common_error_title)
        textMessage.text = args.message ?: getString(DS.string.common_error_message)

        buttonPrimary.text = getString(DS.string.common_try_again)
        buttonPrimary.setOnClickListener { viewModel.onRefresh() }

        println("<L> error fragment cause: ${args.message}")
    }

    companion object {
        fun create(message: String?): ErrorFragment = ErrorFragment()
            .withArgs { putArguments(ErrorArgs(message = message?.capitalizeFirstChar())) }
    }
}
