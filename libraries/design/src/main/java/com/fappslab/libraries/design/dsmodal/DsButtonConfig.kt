package com.fappslab.libraries.design.dsmodal

import androidx.annotation.StringRes

class DsButtonConfig {
    @StringRes
    var buttonTextRes: Int? = null
    var buttonText: String? = null
    var buttonAction: () -> Unit = {}
}
