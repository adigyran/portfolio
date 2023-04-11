plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.mappers.doctors"
}

dependencies {
    implementation(project(":core:data:base"))
    implementation(project(":core:data:doctors"))
    implementation(project(":core:network:model"))
    implementation(libs.kodein)
    implementation(libs.kotlinx.time)
}