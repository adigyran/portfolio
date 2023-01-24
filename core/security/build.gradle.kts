plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.security"
}

dependencies {
    implementation(project(":core:util"))
    implementation(libs.kodein.framework.androidx)
}