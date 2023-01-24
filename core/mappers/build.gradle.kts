plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.mappers"
}

dependencies {
    implementation(project(":core:util"))
    implementation(project(":core:data"))
    implementation(project(":core:network"))
    implementation(libs.kodein.framework.androidx)
    implementation(libs.kotlinx.time)
}