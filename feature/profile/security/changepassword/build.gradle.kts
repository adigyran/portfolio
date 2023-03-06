plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.profile.security.changepassword"
}

dependencies {
    implementation(project(":core:ui:delegates:components:fields:password"))
    implementation(project(":core:ui:delegates:components:labels:headlinetwoline"))
}