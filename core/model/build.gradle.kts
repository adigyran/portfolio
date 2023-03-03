plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.model"
}

dependencies {
    implementation(project(":core:localisation"))

}