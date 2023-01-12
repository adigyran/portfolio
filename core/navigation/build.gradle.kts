plugins {
    id("patientapp.android.library")
}

android {
    namespace = "com.aya.digital.core.navigation" }

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.cicerone)
    implementation(libs.kodein)
    implementation(libs.kodein.framework.androidx)
}