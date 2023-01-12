plugins {
    id("patientapp.android.library")
}

android {
    namespace = "com.aya.digital.core.di"
}

dependencies {
    implementation(project(":core:dibase"))
    implementation(libs.kodein.framework.androidx)
}