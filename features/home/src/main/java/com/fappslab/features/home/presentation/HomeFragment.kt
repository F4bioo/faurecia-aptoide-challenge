package com.fappslab.features.home.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.fappslab.aptoide.features.home.R
import com.fappslab.aptoide.features.home.databinding.HomeFragmentBinding
import com.fappslab.features.home.domain.model.App
import com.fappslab.features.home.presentation.adapter.editors.EditorsAdapter
import com.fappslab.features.home.presentation.adapter.tops.TopsAdapter
import com.fappslab.features.home.presentation.adapter.trending.TrendingAdapter
import com.fappslab.features.home.presentation.extension.animHandle
import com.fappslab.features.home.presentation.extension.commonSetup
import com.fappslab.features.home.presentation.extension.onMenuItem
import com.fappslab.features.home.presentation.extension.showAboutBottomSheet
import com.fappslab.features.home.presentation.extension.showErrorBottomSheet
import com.fappslab.features.home.presentation.viewmodel.HomeViewAction
import com.fappslab.features.home.presentation.viewmodel.HomeViewModel
import com.fappslab.libraries.arch.viewbinding.viewBinding
import com.fappslab.libraries.arch.viewmodel.onViewAction
import com.fappslab.libraries.arch.viewmodel.onViewState
import com.fappslab.libraries.navigation.DetailsNavigation
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

internal class HomeFragment : Fragment(R.layout.home_fragment) {

    private val binding: HomeFragmentBinding by viewBinding()
    private val viewModel: HomeViewModel by sharedViewModel()
    private val detailsNavigation: DetailsNavigation by inject()
    private val adapterEditors by lazy { EditorsAdapter(viewModel::onClickItem) }
    private val adapterTops by lazy { TopsAdapter(viewModel::onClickItem) }
    private val trendingAdapter by lazy { TrendingAdapter(viewModel::onClickItem) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservables()
        setupListeners()
        setupRecyclers()
    }

    private fun setupObservables() {
        onViewState(viewModel) { state ->
            submitListState(state.apps)
            displayedChildState(state.flipperChild)
            refreshLayoutState(state.shouldShowRefreshing)
            emptyViewMessageState(state.emptyViewMessage)
            lottieAnimHandleState(state.shouldAnimLottie)
        }

        onViewAction(viewModel) { action ->
            when (action) {
                is HomeViewAction.Details -> navigateToDetailsAction(action)
                is HomeViewAction.About -> showAboutBottomSheetAction(action)
                is HomeViewAction.Error -> showErrorBottomSheetAction(action)
            }
        }
    }

    private fun showErrorBottomSheetAction(action: HomeViewAction.Error) {
        showErrorBottomSheet(action.shouldShow, action.message, viewModel::onErrorDismiss)
    }

    private fun showAboutBottomSheetAction(action: HomeViewAction.About) {
        showAboutBottomSheet(action.shouldShow, viewModel::onAboutDismiss)
    }

    private fun emptyViewMessageState(message: String?) {
        binding.includeEmptyState.textEmptyMessage.text = message
    }

    private fun lottieAnimHandleState(shouldAnim: Boolean) {
        binding.includeEmptyState.lottieAnim.animHandle(shouldAnim)
    }

    private fun setupListeners() = binding.run {
        refreshLayout.setOnRefreshListener { viewModel.onRefresh() }
        includeEmptyState.buttonEmpty.setOnClickListener { viewModel.onEmptyButtonClicked() }
        onMenuItem(toolbarHome, viewLifecycleOwner) { viewModel.onMenuClicked(idRes = it) }
    }

    private fun setupRecyclers() = binding.run {
        recyclerEditors.adapter = adapterEditors
        recyclerEditors.commonSetup()
        recyclerTops.adapter = adapterTops
        recyclerTops.commonSetup()
        recyclerTrending.adapter = trendingAdapter
        recyclerTrending.commonSetup()
    }

    private fun navigateToDetailsAction(action: HomeViewAction.Details) = context?.let {
        detailsNavigation.create(context = it, action.packageName)
            .also(::startActivity)
    }

    private fun submitListState(apps: List<App>?) {
        adapterEditors.submitList(apps?.take(n = 5))
        trendingAdapter.submitList(apps?.shuffled())
        adapterTops.submitList(apps?.shuffled())
        apps?.let { viewModel.onSubmitList() }
    }

    private fun displayedChildState(flipperChild: Int) {
        binding.flipperContainer.displayedChild = flipperChild
    }

    private fun refreshLayoutState(isRefreshing: Boolean) {
        binding.refreshLayout.isRefreshing = isRefreshing
    }

    companion object {
        fun create(): HomeFragment = HomeFragment()
    }
}
