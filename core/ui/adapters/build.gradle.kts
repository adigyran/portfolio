plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.ui.adapters"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:ext"))
    implementation(libs.androidx.recyclerview)
    implementation(libs.adapter.delegates)
    implementation(libs.adapter.delegates.view.binding)
}
