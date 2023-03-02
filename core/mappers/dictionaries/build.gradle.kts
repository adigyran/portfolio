plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.mappers.dictionaries"
}

dependencies {
    implementation(project(":core:data:base"))
    implementation(project(":core:data:dictionaries"))
    implementation(project(":core:network:model"))
    implementation(libs.kotlinx.time)
    implementation(libs.kodein)

}