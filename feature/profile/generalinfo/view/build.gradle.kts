plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.profile.generalinfo.edit"
}

dependencies {
    implementation(project(":core:ui:delegates:features:profile:generalinfo"))
    implementation(project(":core:model"))
    implementation(libs.kotlinx.time)


}