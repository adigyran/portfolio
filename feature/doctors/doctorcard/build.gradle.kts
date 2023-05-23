plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.doctors.doctorcard"
}

dependencies {
    implementation(project(":core:domain:doctors"))
    implementation(project(":core:domain:schedule"))
    implementation(project(":core:domain:base"))
    implementation(project(":core:ui:delegates:features:doctorcard:doctorslot"))
    implementation(project(":core:ui:delegates:features:doctorcard:doctordetails"))

    implementation(libs.kotlinx.time)
}