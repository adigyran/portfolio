plugins {
    id("healthapp.android.delegate")
}

android {
    namespace = "com.aya.digital.core.ui.delegates.features.auth.signup"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:ui:delegates:components:fields:emailphone"))
    implementation(project(":core:ui:delegates:components:fields:password"))
    implementation(project(":core:ui:delegates:components:fields:name"))
    implementation(project(":core:ui:delegates:components:fields:selection"))
    implementation(project(":core:ui:delegates:components:labels:headline"))

}
