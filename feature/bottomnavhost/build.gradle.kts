plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.feature.bottomnavhost"
}

dependencies {
    implementation(project(":core:domain:profile"))
}