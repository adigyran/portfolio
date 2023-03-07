plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.auth.signin.restorepassword"
}

dependencies {
    implementation(project(":core:domain:auth"))
    implementation(project(":core:ui:delegates:components:fields:emailphone"))
    implementation(project(":core:ui:delegates:components:fields:password"))
    implementation(project(":core:ui:delegates:components:labels:headline"))
}