
plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.network.main"
}

dependencies {
    api(project(":core:networkbase"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.kodein)
    implementation(libs.bundles.retrofit)
    implementation(libs.okhttp.interceptor)
    implementation(libs.moshi.kotlin)
    kapt(libs.moshi.kotlin.codegen)

}