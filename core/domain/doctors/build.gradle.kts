plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.domain.doctors"
}

dependencies {
    implementation(project(":core:dibase"))
    implementation(libs.rxkotlin)
    implementation(libs.kotlinx.time)
    implementation(libs.kodein)
}