plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.feature.bottomdialogs.codedialog"

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    buildFeatures {
        compose = true
    }

}

dependencies {
    implementation(libs.pinview)
    implementation(libs.otpview)
}