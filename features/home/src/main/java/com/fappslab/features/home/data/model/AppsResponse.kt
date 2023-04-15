package com.fappslab.features.home.data.model


import com.google.gson.annotations.SerializedName

internal data class AppsResponse(
    @SerializedName("responses") val responses: Responses?
) {

    internal data class Responses(
        @SerializedName("listApps") val listApps: ListAppsResponse?
    ) {

        internal data class ListAppsResponse(
            @SerializedName("datasets") val datasets: DatasetsResponse?
        ) {

            internal data class DatasetsResponse(
                @SerializedName("all") val all: AllResponse?
            ) {

                internal data class AllResponse(
                    @SerializedName("data") val data: DataResponse?
                ) {

                    internal data class DataResponse(
                        @SerializedName("total") val total: Int?,
                        @SerializedName("offset") val offset: Int?,
                        @SerializedName("limit") val limit: Int?,
                        @SerializedName("next") val next: Int?,
                        @SerializedName("hidden") val hidden: Int?,
                        @SerializedName("list") val list: List<AppResponse>?
                    ) {

                        internal data class AppResponse(
                            @SerializedName("id") val id: Int?,
                            @SerializedName("name") val name: String?,
                            @SerializedName("package") val packageName: String?,
                            @SerializedName("vercode") val versionCode: Int?,
                            @SerializedName("downloads") val downloads: Int?,
                            @SerializedName("graphic") val graphic: String?,
                            @SerializedName("icon") val icon: String?,
                            @SerializedName("store_name") val storeName: String?
                        )
                    }
                }
            }
        }
    }
}
