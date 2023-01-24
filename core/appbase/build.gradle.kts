plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.appbase"
}

dependencies {
    implementation(project(":core:util"))
    implementation(project(":core:dibase"))
    implementation(libs.kodein.framework.androidx)
}
