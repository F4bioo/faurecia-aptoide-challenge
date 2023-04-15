package com.fappslab.features.home.domain.model

internal data class App(
    val id: Int,
    val name: String,
    val packageName: String,
    val versionCode: Int,
    val downloads: Int,
    val graphic: String,
    val icon: String,
    val storeName: String
)
