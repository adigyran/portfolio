plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.profile.security.changeemailphone"
}

dependencies {
    implementation(project(":core:ui:delegates:components:fields:emailphone"))
    implementation(project(":core:ui:delegates:components:labels:headlinetwoline"))
}