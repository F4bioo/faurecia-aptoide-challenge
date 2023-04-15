package com.fappslab.features.home.domain.stub

import com.fappslab.features.home.domain.model.App
import com.fappslab.features.home.domain.model.Apps

internal fun appsStub() =
    Apps(
        list = listOf(appStub(), appStub())
    )

internal fun appStub() =
    App(
        id = 64198900,
        storeName = "mesigior",
        name = "Chrome Canary (Unstable)",
        packageName = "com.chrome.canary",
        versionCode = 555500020,
        downloads = 34560,
        graphic = "https://pool.img.aptoide.com/mesigior/6061351271bc49a24471b3a791a14343_fgraphic.png",
        icon = "https://pool.img.aptoide.com/mesigior/cea3b2901a0fa9c61e766fd724cc5d5d_icon.png"
    )
