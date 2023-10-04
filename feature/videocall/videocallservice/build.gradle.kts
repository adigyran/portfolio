plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.videocall.videocallservice"
}

dependencies {
    implementation(project(":core:appbase"))
    implementation(project(":core:domain:appointment"))
    implementation(project(":feature:rootcontainer"))
}