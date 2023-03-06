plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.profile.security.clinicinfo"
}

dependencies {
    implementation(project(":core:ui:delegates:features:profile:clinicinfo"))

}