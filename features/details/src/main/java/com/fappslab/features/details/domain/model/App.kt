package com.fappslab.features.details.domain.model

internal data class App(
    val id: Int,
    val name: String,
    val packageName: String,
    val size: Int,
    val graphic: String,
    val icon: String,
    val added: String,
    val modified: String,
    val updated: String,
    val downloads: Int,
    val age: Age,
    val developer: Developer,
    val store: Store,
    val apk: Apk,
    val content: Content
) {

    internal data class Age(
        val name: String,
        val title: String,
    )

    internal data class Developer(
        val id: Int,
        val email: String,
        val name: String,
        val privacy: String,
        val website: String
    )

    internal data class Store(
        val id: Int,
        val avatar: String,
        val name: String
    )

    internal data class Apk(
        val versionName: String,
        val added: String,
        val path: String,
        val pathAlt: String,
        val fileSize: Int,
        val malware: Malware,
        val flags: Flags,
        val usedPermissions: List<String>
    )

    internal data class Content(
        val summary: String,
        val description: String,
        val screenshots: List<String>
    )
}
