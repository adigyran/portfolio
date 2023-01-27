plugins {
    id("healthapp.android.library")
    id("kotlin-parcelize")
}

android {
    namespace = "com.aya.digital.core.data"
}

dependencies {
    implementation(project(":core:util"))
    implementation(project(":core:networkbase"))
    implementation(project(":core:network"))
    implementation(libs.kodein)
    implementation(libs.rxkotlin)
    implementation(libs.kotlinx.time)
    implementation(libs.open.auth)
}