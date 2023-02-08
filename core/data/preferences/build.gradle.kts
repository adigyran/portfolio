plugins {
    id("healthapp.android.library")
    id("kotlin-parcelize")
}

android {
    namespace = "com.aya.digital.core.data.preferences"
}

dependencies {
    implementation(project(":core:data:base"))
    implementation(project(":core:network:model"))
    implementation(libs.rxkotlin)
}