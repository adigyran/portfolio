plugins {
    id("healthapp.android.delegate")
}

android {
    namespace = "ccom.aya.digital.core.ui.delegates.components.fields.validated"

    buildFeatures {
        viewBinding = true
    }

}

dependencies {
    implementation(libs.decoro)
}
