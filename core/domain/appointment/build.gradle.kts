plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.domain.appointment"
}

dependencies {
    implementation(project(":core:dibase"))
    implementation(project(":core:ext"))
    implementation(project(":core:util"))

    implementation(project(":core:domain:base"))
    implementation(project(":core:data:base"))
    implementation(project(":core:data:appointment"))
    implementation(project(":core:data:doctors"))
    implementation(project(":core:network:model"))
    implementation(project(":core:navigation"))
    implementation(libs.rxkotlin)
    implementation(libs.kotlinx.time)
    implementation(libs.kodein)
}