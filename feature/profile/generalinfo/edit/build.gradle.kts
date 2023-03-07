plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.profile.generalinfo.edit"
}

dependencies {
    implementation(project(":core:domain:profile"))
    implementation(project(":core:ui:delegates:components:fields:name"))
    implementation(project(":core:ui:delegates:components:fields:validatednumber"))
    implementation(project(":core:ui:delegates:components:fields:dropdown"))
    implementation(project(":core:ui:delegates:components:fields:selection"))
    implementation(project(":core:model"))
    implementation(libs.kotlinx.time)
}