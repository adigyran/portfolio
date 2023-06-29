plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.domain.base"
}

dependencies {
    implementation(project(":core:dibase"))
    implementation(project(":core:ext"))
    implementation(project(":core:util"))
    implementation(project(":core:data:progress"))
    implementation(project(":core:data:appointment"))
    implementation(project(":core:data:doctors"))
    implementation(libs.rxkotlin)
    implementation(libs.kotlinx.time)
    implementation(libs.kodein)
}