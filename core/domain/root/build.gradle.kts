plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.domain.root"
}

dependencies {
    implementation(project(":core:ext"))
    implementation(project(":core:util"))
    implementation(project(":core:dibase"))
    implementation(project(":core:data:base"))
    implementation(project(":core:data:profile"))
    implementation(project(":core:network:model"))
    implementation(project(":core:data:progress"))
    implementation(project(":core:model"))
    implementation(libs.rxkotlin)
    implementation(libs.kotlinx.time)
    implementation(libs.kodein)
}