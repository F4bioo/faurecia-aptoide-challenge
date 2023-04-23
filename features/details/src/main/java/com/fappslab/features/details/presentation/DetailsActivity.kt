package com.fappslab.features.details.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.parseAsHtml
import com.fappslab.aptoide.features.details.R
import com.fappslab.aptoide.features.details.databinding.DetailsActivityBinding
import com.fappslab.features.details.domain.model.App
import com.fappslab.features.details.presentation.adapter.ScreenshotsAdapter
import com.fappslab.features.details.presentation.extension.animHandle
import com.fappslab.features.details.presentation.model.DetailsArgs
import com.fappslab.features.details.presentation.viewmodel.DetailsViewAction
import com.fappslab.features.details.presentation.viewmodel.DetailsViewModel
import com.fappslab.features.details.presentation.viewmodel.DetailsViewState
import com.fappslab.libraries.arch.args.view.createIntent
import com.fappslab.libraries.arch.args.view.putArguments
import com.fappslab.libraries.arch.args.view.viewArgs
import com.fappslab.libraries.arch.network.downloader.Downloader
import com.fappslab.libraries.arch.viewbinding.viewBinding
import com.fappslab.libraries.arch.viewmodel.onViewAction
import com.fappslab.libraries.arch.viewmodel.onViewState
import com.fappslab.libraries.design.extension.loadImage
import com.fappslab.libraries.design.extension.setLightNavigationBarIcons
import com.fappslab.libraries.design.extension.setTint
import com.fappslab.libraries.design.extension.setTransparentStatusBar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import com.fappslab.aptoide.libraries.design.R as DS

internal class DetailsActivity : AppCompatActivity(R.layout.details_activity) {

    private val binding: DetailsActivityBinding by viewBinding()
    private val viewModel: DetailsViewModel by viewModel { parametersOf(args.packageName) }
    private val adapterScreenshots by lazy { ScreenshotsAdapter() }
    private val downloader: Downloader by inject()
    private val args: DetailsArgs by viewArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransparentStatusBar()
        setLightNavigationBarIcons()
        setupObservables()
        setupListeners()
        setupRecycler()
    }

    private fun setupObservables() {
        onViewState(viewModel) { state ->
            state.viewState()
            submitListState(state.screenshots)
            displayedChildState(state.flipperChild)
            errorMessageState(state.errorMessage)
            shimmerAnimHandleState(state.shouldAnimShimmer)
            lottieAnimHandleState(state.shouldAnimLottie)
        }

        onViewAction(viewModel) { action ->
            when (action) {
                is DetailsViewAction.Download -> downloadAction(action)
                DetailsViewAction.FinishView -> finish()
            }
        }
    }

    private fun setupListeners() = binding.run {
        includeHeader.buttonBack.setOnClickListener { viewModel.onBack() }
        includeHeader.buttonDownload.setOnClickListener { viewModel.onDownload() }
        includeEmptyState.buttonPrimary.setOnClickListener { viewModel.onTryAgain() }
        includeEmptyState.buttonSecondary.setOnClickListener { viewModel.onBack() }
    }

    private fun setupRecycler() = binding.run {
        recyclerScreenshots.adapter = adapterScreenshots
        recyclerScreenshots.itemAnimator = null
    }

    private fun errorMessageState(errorMessage: String?) {
        binding.includeEmptyState.textMessage.text =
            errorMessage ?: getString(DS.string.common_error_message)
    }

    private fun DetailsViewState.viewState() = app?.run {
        headerState(rankColor)
        descriptionState()
        moreState()
    }

    private fun App.headerState(@ColorRes rankColor: Int) {
        binding.includeHeader.let {
            it.imageDetailsAvatar.loadImage(icon)
            it.imageRank.setTint(rankColor)
            it.textRank.text = apk.malware.rank
            it.textTile.text = name
            it.textDownloads.text = downloads
            it.textSize.text = apk.fileSize
        }
    }

    private fun App.descriptionState() {
        binding.includeDescription
            .textDescription.text = content.description
    }

    private fun App.moreState() = binding.includeMore.let {
        it.textEmail.text = getString(R.string.details_email, developer.email).parseAsHtml()
        it.textName.text = getString(R.string.details_name, developer.name).parseAsHtml()
        it.textPrivacy.text = getString(R.string.details_privacy, developer.privacy).parseAsHtml()
    }

    private fun submitListState(screenshots: List<String>?) {
        adapterScreenshots.submitList(screenshots)
    }

    private fun displayedChildState(flipperChild: Int) {
        binding.flipperContainer.displayedChild = flipperChild
    }

    private fun shimmerAnimHandleState(shouldAnim: Boolean) {
        binding.includeLoadingState.shimmerAnim.animHandle(shouldAnim)
    }

    private fun lottieAnimHandleState(shouldAnim: Boolean) {
        binding.includeEmptyState.lottieAnimDetailsEmpty.animHandle(shouldAnim)
    }

    private fun downloadAction(action: DetailsViewAction.Download) {
        downloader.downloadApk(action.apkUrl, action.apkName)
    }

    companion object {
        fun create(context: Context, args: DetailsArgs): Intent =
            context.createIntent<DetailsActivity> { putArguments(args) }
    }
}
