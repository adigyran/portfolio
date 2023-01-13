plugins {
    id("patientapp.android.library")
}

android {
    namespace = "com.aya.digital.core.di"
}

dependencies {
    implementation(project(":core:dibase"))
    implementation(project(":core:network"))
    implementation(project(":core:datasources"))
    implementation(libs.kodein.framework.androidx)
}