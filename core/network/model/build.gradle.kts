plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.network.model"
}

dependencies {
    api(project(":core:networkbase"))
    implementation(libs.rxkotlin)
    implementation(libs.retrofit)
    implementation(libs.kotlinx.time)
    implementation(libs.moshi.kotlin)
    kapt(libs.moshi.kotlin.codegen)
}