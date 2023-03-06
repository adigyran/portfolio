plugins {
    id("healthapp.android.delegate")
}

android {
    namespace = "com.aya.digital.core.ui.delegates.features.profile.insurance"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:model"))

}
