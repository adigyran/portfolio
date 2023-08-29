plugins {
    id("healthapp.android.library")
    id("kotlin-parcelize")
}

android {
    namespace = "com.aya.digital.core.data.profile"
}

dependencies {
    implementation(project(":core:data:base"))
    implementation(project(":core:data:dictionaries"))
    implementation(project(":core:network:model"))
    implementation(libs.rxkotlin)
    implementation(libs.kotlinx.time)
    implementation(libs.open.auth)
}