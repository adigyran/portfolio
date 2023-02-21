plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.auth.signin"
}

dependencies {
    implementation(project(":core:ui:delegates:features:auth:signin"))
    implementation(project(":core:ui:delegates:buttons:filledbutton"))
}