plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.domain"
}

dependencies {
    implementation(project(":core:dibase"))
    implementation(project(":core:data"))
    implementation(project(":core:repository"))
    implementation(libs.rxkotlin)
    implementation(libs.kotlinx.time)
    implementation(libs.kodein)
}