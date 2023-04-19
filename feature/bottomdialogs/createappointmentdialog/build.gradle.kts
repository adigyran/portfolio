plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.feature.bottomdialogs.createappointmentdialog"
}

dependencies {
    implementation(project(":core:domain:doctors"))
    implementation(project(":core:domain:schedule"))
    implementation(project(":core:ui:delegates:features:doctorcard:doctorslot"))
    implementation(libs.kotlinx.time)
}