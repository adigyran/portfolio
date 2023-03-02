plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.auth.signin"
}

dependencies {
    implementation(project(":core:domain:auth"))
    implementation(project(":core:ui:delegates:features:auth:signin"))
    implementation(project(":core:ui:delegates:components:buttons:filledbutton"))
    implementation(project(":core:ui:delegates:components:fields:emailphone"))
    implementation(project(":core:ui:delegates:components:fields:password"))
    implementation(project(":core:ui:delegates:components:labels:headline"))
    implementation(project(":core:ui:delegates:components:labels:spannablehelper"))
}