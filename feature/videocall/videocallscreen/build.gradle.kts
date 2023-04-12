plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.videocall.videocallscreen"
}

dependencies {
    implementation(project(":core:domain:doctors"))

}