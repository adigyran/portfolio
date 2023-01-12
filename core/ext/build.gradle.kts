plugins {
    id("patientapp.android.library")
}

android {
    namespace = "com.aya.digital.core.ext"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.recyclerview)
}