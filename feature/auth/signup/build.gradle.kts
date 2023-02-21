plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.feature.auth.signup"
}

dependencies {
    implementation(project(":core:ui:delegates:features:auth:signup"))
    implementation(project(":core:ui:delegates:buttons:filledbutton"))
}