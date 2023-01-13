plugins {
    id("patientapp.android.library")
}

android {
    namespace = "com.aya.digital.core.data"
}

dependencies {
    implementation(project(":core:util"))
    implementation(project(":core:network"))
    implementation(libs.kodein.framework.androidx)
    implementation(libs.rxkotlin)
    implementation(libs.kotlinx.time)
    implementation(libs.open.auth)
}