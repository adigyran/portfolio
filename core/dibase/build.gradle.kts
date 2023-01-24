plugins {
    id("healthapp.android.library")

}

android {
    namespace = "com.aya.digital.core.dibase"
}

dependencies {
    implementation(project(":core:util"))
    implementation(libs.androidx.appcompat)
    implementation(libs.kodein.framework.androidx)
}