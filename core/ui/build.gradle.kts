plugins {
    id("patientapp.android.library")
}

android {
    namespace = "com.aya.digital.core.ui"
}

dependencies {
    implementation(project(":core:uibase"))
}