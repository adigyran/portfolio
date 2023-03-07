plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.util"
}

dependencies {
    implementation(project(":core:baseresources"))
    implementation(project(":core:ext"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.kodein)
    implementation(libs.timber)

}