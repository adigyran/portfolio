plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.designsystem"
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:baseresources"))
    implementation(project(":core:localisation"))
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material.design)
}