plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.domain.schedule"
}

dependencies {
    implementation(project(":core:dibase"))
    implementation(project(":core:ext"))
    implementation(project(":core:util"))
    implementation(project(":core:data:base"))
    implementation(project(":core:data:schedule"))
    implementation(project(":core:network:model"))
    implementation(libs.rxkotlin)
    implementation(libs.kotlinx.time)
    implementation(libs.kodein)
}