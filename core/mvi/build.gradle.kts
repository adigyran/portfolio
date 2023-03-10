plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.mvi"
}

dependencies {
    implementation(project(":core:baseresources"))
    implementation(project(":core:localisation"))
    implementation(project(":core:util"))
    implementation(project(":core:networkbase"))
    implementation(project(":core:data:base"))
    implementation(libs.rxkotlin)
    implementation(libs.rxandroid)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.lifecycle.viewModel)
    implementation(libs.orbit.viewmodel)
    implementation(libs.eventbus)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics.ktx)
    implementation(libs.kodein.framework.androidx)
}