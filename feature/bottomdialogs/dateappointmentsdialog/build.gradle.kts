plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.feature.bottomdialogs.dateappointmentsdialog"
}

dependencies {
    implementation(project(":core:ext"))
    implementation(project(":core:domain:base"))
    implementation(project(":core:domain:appointment"))
    implementation(project(":core:ui:delegates:features:appointments:patientappointment"))
    implementation(libs.kotlinx.time)
}