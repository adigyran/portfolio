plugins {
    id("healthapp.android.delegate")
}

android {
    namespace = "com.aya.digital.core.ui.delegates.features.auth.signin"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:ui:delegates:fields:emailphone"))
    implementation(project(":core:ui:delegates:fields:password"))
}
