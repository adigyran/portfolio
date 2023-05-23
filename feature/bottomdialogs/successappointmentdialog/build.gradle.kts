plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.feature.bottomdialogs.successappointmentdialog"
}

dependencies {
    implementation(project(":core:domain:doctors"))
    implementation(project(":core:domain:appointment"))
    implementation(project(":core:domain:base"))
    implementation(libs.kotlinx.time)
}