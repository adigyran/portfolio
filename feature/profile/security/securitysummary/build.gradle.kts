plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.profile.security.securitysummary"
}

dependencies {
    implementation(project(":core:ui:delegates:features:profile:securitysummary"))

}