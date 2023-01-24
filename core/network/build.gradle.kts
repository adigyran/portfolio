
plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.network"
}

dependencies {
    implementation(project(":core:networkbase"))
    implementation(libs.kodein.framework.androidx)
    implementation(libs.rxkotlin)
    implementation(libs.retrofit)
    implementation(libs.retrofit.adapter.rxjava3)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.okhttp.interceptor)
    implementation(libs.moshi.kotlin)
    kapt(libs.moshi.kotlin.codegen)
    implementation(libs.kotlinx.time)
    implementation(libs.open.auth)

}