plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.profile.generalinfo.view"
}

dependencies {
    implementation(project(":core:domain:profile"))
    implementation(project(":core:ui:delegates:features:profile:generalinfo"))
    implementation(project(":core:model"))
    implementation(libs.kotlinx.time)
}