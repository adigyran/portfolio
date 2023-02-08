plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.mappers.preferences"
}

dependencies {
    implementation(project(":core:data:base"))
    implementation(project(":core:data:preferences"))
    implementation(project(":core:network:model"))
}