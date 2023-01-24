plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.networkbase"
}

dependencies {
    implementation(libs.moshi.kotlin)
    kapt(libs.moshi.kotlin.codegen)
}