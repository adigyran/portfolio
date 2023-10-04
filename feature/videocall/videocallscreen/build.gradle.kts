plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.videocall.videocallscreen"
}

dependencies {
    implementation(project(":core:appbase"))
    implementation(project(":core:domain:base"))
    implementation(project(":core:domain:appointment"))
    implementation(libs.twilio)
    implementation(libs.twilio.audioswitch)
    implementation(libs.kotlinx.time)
}