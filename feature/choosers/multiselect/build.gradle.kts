plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.choosers.multiselect"
}

dependencies {
    implementation(project(":core:ext"))
    implementation(project(":core:domain:dictionaries"))
    implementation(project(":core:ui:delegates:features:choosers:multiselect"))
}