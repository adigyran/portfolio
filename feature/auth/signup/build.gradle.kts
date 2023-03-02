plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.feature.auth.signup"
}

dependencies {
    implementation(project(":core:domain:auth"))
    implementation(project(":core:domain:dictionaries"))
    implementation(project(":core:ui:delegates:features:auth:signup"))
    implementation(project(":core:ui:delegates:components:buttons:filledbutton"))
    implementation(project(":core:ui:delegates:components:fields:emailphone"))
    implementation(project(":core:ui:delegates:components:fields:password"))
    implementation(project(":core:ui:delegates:components:fields:name"))
    implementation(project(":core:ui:delegates:components:fields:selection"))
    implementation(project(":core:ui:delegates:components:labels:headline"))
}