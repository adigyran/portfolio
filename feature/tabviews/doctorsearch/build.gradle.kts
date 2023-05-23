plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.tabviews.doctorsearch"
}

dependencies {
    implementation(project(":core:ext"))
    implementation(project(":core:domain:doctors"))
    implementation(project(":core:domain:base"))
    implementation(project(":core:ui:delegates:features:doctors:doctoritem"))
}