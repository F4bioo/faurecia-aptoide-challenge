plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()

    maven {
        url = uri("https://jcenter.bintray.com/")
        content {
            includeGroup("org.jetbrains.kotlin")
        }
    }
}
