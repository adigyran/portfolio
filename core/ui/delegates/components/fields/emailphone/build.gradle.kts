plugins {
    id("healthapp.android.delegate")
}

android {
    namespace = "com.aya.digital.core.ui.delegates.components.fields.emailphone"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.decoro)
}

