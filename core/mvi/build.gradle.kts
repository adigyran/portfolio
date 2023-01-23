plugins {
    id("patientapp.android.library")
}

android {
    namespace = "com.aya.digital.core.mvi"
}

dependencies {
    implementation(project(":core:baseresources"))
    implementation(project(":core:util"))
    implementation(project(":core:networkbase"))
    implementation(libs.rxkotlin)
    implementation(libs.rxandroid)
    implementation(libs.eventbus)
    implementation(libs.kodein.framework.androidx)

}