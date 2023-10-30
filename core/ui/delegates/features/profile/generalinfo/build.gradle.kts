plugins {
    id("healthapp.android.delegate")
}

android {
    namespace = "com.aya.digital.core.ui.delegates.features.profile.generalinfo"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.gms.maps)
    implementation(libs.gms.maps.utils)
}