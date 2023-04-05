plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.ui.base"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:ext"))
    implementation(project(":core:ui:core"))
    implementation(project(":core:mvi"))
    implementation(project(":core:dibase"))
    implementation(project(":core:navigation"))
    implementation(project(":core:appbase"))
    implementation(project(":core:util"))
    implementation(project(":core:localisation"))
    implementation(libs.orbit.viewmodel)
    implementation(libs.rxrelay)
    implementation(libs.cicerone)
    implementation(libs.kodein)
    implementation(libs.androidx.appcompat)
    implementation(libs.material.design)
    implementation(libs.decoro)
}
