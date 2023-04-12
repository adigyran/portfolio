plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.videocall.videocallscreen"
}

dependencies {
    implementation(project(":core:domain:doctors"))
    implementation(project(":core:appbase"))
    implementation(libs.twilio)
    implementation(libs.twilio.audioswitch)
}