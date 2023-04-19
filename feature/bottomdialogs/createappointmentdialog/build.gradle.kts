plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.feature.bottomdialogs.createappointmentdialog"

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    buildFeatures {
        compose = true
    }

}

dependencies {
    implementation(project(":core:domain:doctors"))
    implementation(project(":core:domain:schedule"))
    implementation(project(":core:ui:delegates:features:doctorcard:doctorslot"))
    implementation(libs.kotlinx.time)
}