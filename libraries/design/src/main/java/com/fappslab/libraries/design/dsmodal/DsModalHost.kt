package com.fappslab.libraries.design.dsmodal

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.fappslab.aptoide.libraries.design.R
import com.fappslab.aptoide.libraries.design.databinding.LayoutDsmodalHostBinding
import com.fappslab.libraries.arch.viewbinding.viewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DsModalHost : BottomSheetDialogFragment(R.layout.layout_dsmodal_host) {

    private val binding: LayoutDsmodalHostBinding by viewBinding()

    var setFragment: (() -> Fragment)? = null
    var onCloseButton: () -> Unit? = { dismissAllowingStateLoss() }
    var onDismiss: () -> Unit? = { dismissAllowingStateLoss() }
    var shouldBlock: Boolean = false
    var shouldExpanded: Boolean = false

    override fun getTheme() = R.style.DsBottomSheet

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBehavior()
        setupChild()
        setupCloseButton()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismiss()
    }

    private fun setupBehavior() {
        (dialog as? BottomSheetDialog)?.behavior?.apply {
            if (shouldExpanded) {
                state = BottomSheetBehavior.STATE_EXPANDED
            }
            if (shouldBlock) {
                isHideable = true
                isCancelable = false
                isDraggable = false
            }
        }
    }

    private fun setupCloseButton() {
        binding.buttonClose.setOnClickListener {
            onCloseButton()
            dismiss()
        }
    }

    private fun setupChild() {
        setFragment?.let { fragment ->
            childFragmentManager.commit { replace(binding.containerFragment.id, fragment()) }
        }
    }
}
