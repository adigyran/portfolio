plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.ui"
}

dependencies {
    implementation(project(":core:uibase"))
}