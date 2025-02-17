plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap"
}

dependencies {
    implementation(project(":core:ext"))
    implementation(project(":core:domain:doctors"))
    implementation(project(":core:domain:location"))
    implementation(project(":core:domain:base"))
    implementation(project(":core:ui:delegates:features:doctors:doctoritem"))
    implementation(libs.gms.maps)
    implementation(libs.gms.maps.utils)
}