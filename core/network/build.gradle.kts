
plugins {
    id("patientapp.android.library")
}

android {
    namespace = "com.aya.digital.core.network"
    compileSdkPreview = "O"
}

dependencies {
    implementation(project(":core:networkbase"))
    implementation(libs.kodein.framework.androidx)
    implementation(libs.retrofit)
    implementation(libs.retrofit.adapter.rxjava3)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.okhttp.interceptor)
    implementation(libs.moshi.kotlin)
    kapt(libs.moshi.kotlin.codegen)

}