plugins {
    id("healthapp.android.delegate")
}

android {
    namespace = "com.aya.digital.core.ui.delegates.features.appointmentcard.appointmentdetails"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:model"))

}
