plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.domain.appointment"
}

dependencies {
    implementation(project(":core:dibase"))
    implementation(libs.rxkotlin)
    implementation(libs.kotlinx.time)
    implementation(libs.kodein)
}