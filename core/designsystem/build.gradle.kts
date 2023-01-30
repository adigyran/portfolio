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
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material.design)
}