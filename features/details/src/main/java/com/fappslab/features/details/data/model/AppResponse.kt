package com.fappslab.features.details.data.model


import com.google.gson.annotations.SerializedName

internal data class AppResponse(
    @SerializedName("data") val data: DataResponse?
) {

    internal data class DataResponse(
        @SerializedName("id") val id: Int?,
        @SerializedName("name") val name: String?,
        @SerializedName("package") val packageName: String?,
        @SerializedName("size") val size: Int?,
        @SerializedName("graphic") val graphic: String?,
        @SerializedName("icon") val icon: String?,
        @SerializedName("added") val added: String?,
        @SerializedName("modified") val modified: String?,
        @SerializedName("updated") val updated: String?,
        @SerializedName("stats") val stats: StatsResponse?,

        @SerializedName("age") val age: AgeResponse?,
        @SerializedName("developer") val developer: DeveloperResponse?,
        @SerializedName("store") val store: StoreResponse?,
        @SerializedName("file") val apk: ApkResponse?,
        @SerializedName("media") val content: ContentResponse?,
    ) {

        internal data class StatsResponse(
            @SerializedName("downloads") val downloads: Int?
        )

        internal data class AgeResponse(
            @SerializedName("name") val name: String?,
            @SerializedName("title") val title: String?
        )

        internal data class DeveloperResponse(
            @SerializedName("id") val id: Int?,
            @SerializedName("email") val email: String?,
            @SerializedName("name") val name: String?,
            @SerializedName("privacy") val privacy: String?
        )

        internal data class StoreResponse(
            @SerializedName("id") val id: Int?,
            @SerializedName("avatar") val avatar: String?,
            @SerializedName("name") val name: String?
        )

        internal data class ApkResponse(
            @SerializedName("vername") val versionName: String?,
            @SerializedName("added") val added: String?,
            @SerializedName("path") val path: String?,
            @SerializedName("path_alt") val pathAlt: String?,
            @SerializedName("filesize") val fileSize: Int?,
            @SerializedName("malware") val malware: MalwareResponse?,
            @SerializedName("flags") val flags: FlagsResponse?,
            @SerializedName("used_permissions") val usedPermissions: List<String>?
        )

        internal data class ContentResponse(
            @SerializedName("summary") val summary: String?,
            @SerializedName("description") val description: String?,
            @SerializedName("screenshots") val screenshots: List<ScreenshotResponse>?
        )
    }
}
