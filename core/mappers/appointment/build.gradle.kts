plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.mappers.appointment"
}

dependencies {
    implementation(project(":core:data:base"))
    implementation(project(":core:data:appointment"))
    implementation(project(":core:network:model"))
}