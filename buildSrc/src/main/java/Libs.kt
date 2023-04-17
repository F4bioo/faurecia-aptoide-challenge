object Libs {
    // Android base
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx_version}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
    const val material = "com.google.android.material:material:${Versions.material_version}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout_version}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_version}"
    const val fragment = "androidx.fragment:fragment:${Versions.fragment_version}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment_version}"
    const val annotation = "androidx.annotation:annotation:${Versions.annotation_version}"

    // Kotlin
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"

    // Koin
    const val koinCore = "io.insert-koin:koin-core:${Versions.koin_version}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin_version}"
    const val koinScope = "io.insert-koin:koin-androidx-scope:${Versions.koin_version}"
    const val koinViewModel = "io.insert-koin:koin-androidx-viewmodel:${Versions.koin_version}"

    // Square up
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp_version}"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_version}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"

    // Gson
    const val gson = "com.google.code.gson:gson:${Versions.gson_version}"

    // Images
    const val coil = "io.coil-kt:coil:${Versions.coil_version}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie_version}"

    // RX Java
    const val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava_version}"
    const val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid_version}"
}
