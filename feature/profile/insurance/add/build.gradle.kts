plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.profile.insurance.add"
}

dependencies {
    implementation(project(":core:domain:profile"))
    implementation(project(":core:ui:delegates:components:fields:name"))
    implementation(project(":core:ui:delegates:components:fields:selection"))
    implementation(project(":core:ui:delegates:features:profile:insurance"))
}