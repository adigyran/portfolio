plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.profile.insurance.list"
}

dependencies {
    implementation(project(":core:ui:delegates:features:profile:insurance"))

}