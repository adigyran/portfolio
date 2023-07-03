plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.ui.core"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:ext"))
    implementation(project(":core:baseresources"))
    implementation(project(":core:navigation"))
    implementation(project(":core:util"))
    implementation(project(":core:appbase"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.rxkotlin)
    implementation(libs.androidx.appcompat)
    implementation(libs.material.design)
    implementation(libs.constraint.layout)
    implementation(libs.androidx.recyclerview)
    implementation(libs.cicerone)
    implementation(libs.kodein)
}
