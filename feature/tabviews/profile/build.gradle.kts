plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.tabviews.profile"
}

dependencies {
    implementation(project(":core:ui:delegates:features:profile:main"))
    implementation(project(":core:domain:profile"))

}