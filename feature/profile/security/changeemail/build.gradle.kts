plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.profile.security.changeemail"
}

dependencies {
    implementation(project(":core:domain:profile"))
    implementation(project(":core:ui:delegates:components:fields:emailphone"))
    implementation(project(":core:ui:delegates:components:labels:headlinetwoline"))
}