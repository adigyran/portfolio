plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.navigation" }

dependencies {
    implementation(project(":core:dibase"))
    implementation(project(":core:ext"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.kodein)
    implementation(libs.kodein.framework.androidx)
    implementation(libs.cicerone)
}