package com.fappslab.libraries.design.dsmodal

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.fappslab.aptoide.libraries.design.R
import com.fappslab.aptoide.libraries.design.databinding.LayoutDsmodalHostBinding
import com.fappslab.libraries.arch.extension.isNotNull
import com.fappslab.libraries.arch.viewbinding.viewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DsModalHost : BottomSheetDialogFragment(R.layout.layout_dsmodal_host) {

    private val binding: LayoutDsmodalHostBinding by viewBinding()

    private var primaryButtonConfig: DsButtonConfig? = null
    private var secondaryButtonConfig: DsButtonConfig? = null
    private var primaryButtonAction: (() -> Unit)? = null
    private var secondaryButtonAction: (() -> Unit)? = null

    var closeButton: () -> Unit = { dismissAllowingStateLoss() }
    var onDismiss: () -> Unit? = { dismissAllowingStateLoss() }
    var setFragment: (() -> Fragment)? = null
    var shouldBlock: Boolean = false

    @DrawableRes
    var imageRes: Int? = null

    @StringRes
    var titleRes: Int? = null
    var titleText: String? = null

    @StringRes
    var messageRes: Int? = null
    var messageText: String? = null

    var primaryButton: (DsButtonConfig.() -> Unit)? = null
        set(value) {
            field = value
            value?.let { block ->
                primaryButtonConfig = DsButtonConfig().apply(block)
                primaryButtonAction = primaryButtonConfig?.buttonAction
            }
        }
    var secondaryButton: (DsButtonConfig.() -> Unit)? = null
        set(value) {
            field = value
            value?.let { block ->
                secondaryButtonConfig = DsButtonConfig().apply(block)
                secondaryButtonAction = secondaryButtonConfig?.buttonAction
            }
        }

    override fun getTheme(): Int {
        return R.style.DsBottomSheet
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        primaryButtonConfig?.setupPrimaryButton()
        secondaryButtonConfig?.setupSecondaryButton()
        setupBehavior()
        setupDragLine()
        setupCloseButton()
        setupChild()
        setupAvatarImage()
        setupTitle()
        setupMessage()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismiss()
    }

    private fun setupBehavior() {
        (dialog as? BottomSheetDialog)?.behavior?.apply {
            state = BottomSheetBehavior.STATE_EXPANDED
            if (shouldBlock) {
                isHideable = true
                isCancelable = false
                isDraggable = false
            }
        }
    }

    private fun setupDragLine() {
        binding.dragLine.isVisible = shouldBlock.not()
    }

    private fun setupCloseButton() {
        binding.buttonClose.setOnClickListener { closeButton() }
    }

    private fun setupChild() {
        setFragment?.let { fragment ->
            childFragmentManager.commit { replace(binding.containerFragment.id, fragment()) }
        }
    }

    private fun setupAvatarImage() {
        binding.imageAvatar.run {
            imageRes?.let(::setImageResource)
            isVisible = imageRes.isNotNull()
        }
    }

    private fun setupTitle() {
        binding.textTile.run {
            titleRes?.let(::setText)
            titleText?.let { text = it }
            isVisible = shouldShow(pairData = titleRes to titleText)
        }
    }

    private fun setupMessage() {
        binding.textMessage.run {
            messageRes?.let(::setText)
            messageText?.let { text = it }
            isVisible = shouldShow(pairData = messageRes to messageText)
        }
    }

    private fun DsButtonConfig.setupPrimaryButton() {
        binding.buttonPrimary.apply {
            buttonTextRes?.let(::setText)
            buttonText?.let { text = it }
            isVisible = shouldShow(pairData = buttonTextRes to buttonText)
            setOnClickListener { primaryButtonAction?.invoke() }
        }
    }

    private fun DsButtonConfig.setupSecondaryButton() {
        binding.buttonSecondary.apply {
            buttonTextRes?.let(::setText)
            buttonText?.let { text = it }
            isVisible = shouldShow(pairData = buttonTextRes to buttonText)
            setOnClickListener { secondaryButtonAction?.invoke() }
        }
    }

    private fun shouldShow(pairData: Pair<Int?, String?>): Boolean {
        val (textRes, textString) = pairData
        return textRes.isNotNull() or textString.isNotNull()
    }
}
