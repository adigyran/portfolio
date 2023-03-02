plugins {
    id("healthapp.android.delegate")
}

android {
    namespace = "com.aya.digital.core.ui.delegates.features.auth.signin"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:ui:delegates:components:fields:emailphone"))
    implementation(project(":core:ui:delegates:components:fields:password"))
    implementation(project(":core:ui:delegates:components:labels:headline"))
    implementation(project(":core:ui:delegates:components:labels:spannablehelper"))


}
