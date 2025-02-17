plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.profile.prescriptions.list"
}

dependencies {
    implementation(project(":core:domain:profile"))
    implementation(project(":core:domain:prescriptions"))
    implementation(project(":core:ui:delegates:features:profile:insurance"))

}