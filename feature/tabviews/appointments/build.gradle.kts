plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.tabviews.appointments"
}

dependencies {
    implementation(project(":core:ext"))
    implementation(project(":core:domain:base"))
    implementation(project(":core:domain:appointment"))
    implementation(project(":core:ui:delegates:features:appointments:patientappointment"))
    implementation(libs.kotlinx.time)
}