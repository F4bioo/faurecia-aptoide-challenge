package com.fappslab.features.details.data.mapper

import com.fappslab.features.details.data.model.AppResponse
import com.fappslab.features.details.data.model.AppResponse.DataResponse.AgeResponse
import com.fappslab.features.details.data.model.AppResponse.DataResponse.ApkResponse
import com.fappslab.features.details.data.model.AppResponse.DataResponse.ContentResponse
import com.fappslab.features.details.data.model.AppResponse.DataResponse.DeveloperResponse
import com.fappslab.features.details.data.model.AppResponse.DataResponse.StoreResponse
import com.fappslab.features.details.data.model.FlagsResponse
import com.fappslab.features.details.data.model.MalwareResponse
import com.fappslab.features.details.data.model.VoteResponse
import com.fappslab.features.details.domain.model.App
import com.fappslab.features.details.domain.model.App.Age
import com.fappslab.features.details.domain.model.App.Apk
import com.fappslab.features.details.domain.model.App.Content
import com.fappslab.features.details.domain.model.App.Developer
import com.fappslab.features.details.domain.model.App.Store
import com.fappslab.features.details.domain.model.Flags
import com.fappslab.features.details.domain.model.Malware
import com.fappslab.features.details.domain.model.Vote
import com.fappslab.libraries.arch.extension.orZero

internal fun AppResponse.toApp() =
    App(
        id = data?.id.orZero(),
        name = data?.name.orEmpty(),
        packageName = data?.packageName.orEmpty(),
        size = data?.size.orZero(),
        graphic = data?.graphic.orEmpty(),
        icon = data?.icon.orEmpty(),
        added = data?.added.orEmpty(),
        modified = data?.modified.orEmpty(),
        updated = data?.updated.orEmpty(),
        downloads = data?.stats?.downloads.orZero(),
        age = data?.age.toAge(),
        developer = data?.developer.toDeveloper(),
        store = data?.store.toStore(),
        apk = data?.apk.toApk(),
        content = data?.content.toContent()
    )

internal fun AgeResponse?.toAge() =
    Age(
        name = this?.name.orEmpty(),
        title = this?.title.orEmpty()
    )

internal fun DeveloperResponse?.toDeveloper() =
    Developer(
        id = this?.id.orZero(),
        email = this?.email.orEmpty(),
        name = this?.name.orEmpty(),
        privacy = this?.privacy.orEmpty(),
        website = this?.website.orEmpty()
    )

internal fun StoreResponse?.toStore() =
    Store(
        id = this?.id.orZero(),
        avatar = this?.avatar.orEmpty(),
        name = this?.name.orEmpty()
    )

internal fun ApkResponse?.toApk() =
    Apk(
        versionName = this?.versionName.orEmpty(),
        added = this?.added.orEmpty(),
        path = this?.path.orEmpty(),
        pathAlt = this?.pathAlt.orEmpty(),
        fileSize = this?.fileSize.orZero(),
        malware = this?.malware.toMalware(),
        flags = this?.flags.toFlags(),
        usedPermissions = this?.usedPermissions.orEmpty()
    )

internal fun MalwareResponse?.toMalware() =
    Malware(
        rank = this?.rank.orEmpty()
    )

internal fun FlagsResponse?.toFlags() =
    Flags(
        votes = this?.votes?.map { it.toVote() }.orEmpty()
    )

internal fun VoteResponse?.toVote() =
    Vote(
        count = this?.count.orZero(),
        type = this?.type.orEmpty()
    )

internal fun ContentResponse?.toContent() =
    Content(
        summary = this?.summary.orEmpty(),
        description = this?.description.orEmpty(),
        screenshots = this?.screenshots?.map { it.imageUrl.orEmpty() }.orEmpty()
    )
