object TestLibs {
    // Koin
    const val koinTest = "io.insert-koin:koin-test:${Versions.koin_version}"
    const val koinCoreTest = "io.insert-koin:koin-test-core:${Versions.koin_version}"

    // Mockk
    const val mockkUnitTest = "io.mockk:mockk:${Versions.mockk_version}"
    const val mockkAndroidTest = "io.mockk:mockk-android:${Versions.mockk_version}"

    // SquareUp
    const val mockWebserverTest = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebserver_version}"
    const val okhttpTest = "com.squareup.okhttp3:okhttp:${Versions.okhttpTest_version}"

    // Android
    const val runnerTest = "androidx.test:runner:${Versions.testRules_version}"
    const val espressoCoreTest = "androidx.test.espresso:espresso-core:${Versions.espresso_version}"
    const val extJunitTest = "androidx.test.ext:junit:${Versions.androidxJunit_version}"
    const val extJunitKtxTest = "androidx.test.ext:junit-ktx:${Versions.androidxJunit_version}"
    const val fragmentTest = "androidx.fragment:fragment-testing:${Versions.fragment_version}"

    // Kotlin
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin_version}"
    const val kotlinJUnitTest = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin_version}"
    const val kotlinCoroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_version }"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test-jvm:${Versions.coroutines_version}"

    // Others
    const val junitTest = "junit:junit:${Versions.junit_version}"
    const val arcCoreTest = "androidx.arch.core:core-testing:${Versions.coreTest_version}"
    const val turbineTest = "app.cash.turbine:turbine:${Versions.turbine_version}"
}
