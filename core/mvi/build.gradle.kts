plugins {
    id("patientapp.android.library")
}

android {
    namespace = "com.aya.digital.core.mvi"
}

dependencies {
    implementation(project(":core:util"))
    implementation(project(":core:networkbase"))
    implementation(libs.rxkotlin)
    implementation(libs.rxandroid)
    implementation(libs.eventbus)
    implementation(libs.kodein.framework.androidx)
    implementation(libs.mvi.mvicore)
    implementation(libs.mvi.mvicore.android)
    implementation(libs.mvi.mvicore.diff)
    implementation(libs.mvi.android.binder)


}