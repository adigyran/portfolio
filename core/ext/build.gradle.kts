plugins {
    id("patientapp.android.library")
}

android {
    namespace = "com.aya.digital.core.ext"
}

dependencies {
    implementation(project(":core:networkbase"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.recyclerview)
    implementation(libs.rxandroid)
    implementation(libs.rxkotlin)
    implementation(libs.moshi.kotlin)
    kapt(libs.moshi.kotlin.codegen)
    implementation(libs.retrofit)
}