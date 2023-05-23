plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.appointments.appointmentcard"
}

dependencies {
    implementation(project(":core:ext"))
    implementation(project(":core:domain:appointment"))
    implementation(project(":core:domain:base"))
    implementation(project(":core:ui:delegates:features:appointmentcard:appointmentdetails"))
    implementation(project(":core:ui:delegates:features:doctorcard:doctordetails"))
    implementation(libs.kotlinx.time)
}