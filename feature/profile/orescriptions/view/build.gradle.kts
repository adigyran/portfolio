plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.profile.orescriptions.view"
}

dependencies {
    implementation(project(":core:domain:profile"))
    implementation(project(":core:domain:dictionaries"))
    implementation(project(":core:ui:delegates:components:fields:name"))
    implementation(project(":core:ui:delegates:components:fields:selection"))
    implementation(project(":core:ui:delegates:features:profile:insurance"))
}