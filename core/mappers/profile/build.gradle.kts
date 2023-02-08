plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.mappers.profile"
}

dependencies {
    implementation(project(":core:data:base"))
    implementation(project(":core:data:profile"))
    implementation(project(":core:network:model"))
    implementation(libs.kotlinx.time)
}