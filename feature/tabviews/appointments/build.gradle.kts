plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.tabviews.appointments"
}

dependencies {
    implementation(project(":core:ext"))
    implementation(project(":core:ui:delegates:features:appointments:patientappointment"))
}