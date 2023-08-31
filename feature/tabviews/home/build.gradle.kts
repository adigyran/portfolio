plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.tabviews.home"
}

dependencies {
    implementation(project(":core:ui:delegates:features:home:homeitems"))
    implementation(project(":core:domain:base"))
    implementation(project(":core:domain:home"))


}