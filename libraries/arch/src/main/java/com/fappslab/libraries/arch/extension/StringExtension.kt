package com.fappslab.libraries.arch.extension

private const val BYTES_IN_KILOBYTE = 1024.0
private const val THOUSAND = 1000.0
private const val MILLION = 1000000.0

fun String?.orDash() = this ?: "-"

fun String.capitalizeFirstChar(): String =
    replaceFirstChar { it.uppercase() }

fun Int?.toFileSize(): String {
    val bytes = orZero().toDouble()
    if (bytes <= 0) return "-"

    val kilobytes = bytes / BYTES_IN_KILOBYTE
    val megabytes = kilobytes / BYTES_IN_KILOBYTE
    val gigabytes = megabytes / BYTES_IN_KILOBYTE

    return when {
        gigabytes >= 1 -> "%.2f GB".format(gigabytes)
        megabytes >= 1 -> "%.2f MB".format(megabytes)
        kilobytes >= 1 -> "%.2f KB".format(kilobytes)
        else -> "$bytes bytes"
    }
}

fun Int?.toDownloads(): String {
    val downloads = orZero().toDouble()
    if (downloads <= 0) return "-"

    val kValue = downloads / THOUSAND
    val mValue = downloads / MILLION

    return when {
        mValue >= 1 -> "%.2f M".format(mValue)
        kValue >= 1 -> "%.2f k".format(kValue)
        else -> "$this"
    }
}
