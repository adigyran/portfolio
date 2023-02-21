plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.feature.auth.chooser"
}

dependencies {
    implementation(project(":core:ui:delegates:features:auth:chooser"))

}