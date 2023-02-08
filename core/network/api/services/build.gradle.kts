plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.network.services"
}

dependencies {
    api(project(":core:networkbase"))
    api(project(":core:network:model"))
    implementation(libs.rxkotlin)
    implementation(libs.retrofit)
    implementation(libs.kotlinx.time)
}