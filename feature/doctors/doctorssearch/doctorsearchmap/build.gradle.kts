plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap"
}

dependencies {
    implementation(project(":core:ext"))
    implementation(project(":core:domain:doctors"))
    implementation(project(":core:domain:base"))
    implementation(libs.gms.maps)
    implementation(libs.gms.maps.utils)
}