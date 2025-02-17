plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.mappers.schedule"
}

dependencies {
    implementation(project(":core:data:base"))
    implementation(project(":core:util"))
    implementation(project(":core:data:schedule"))
    implementation(project(":core:network:model"))

    implementation(libs.kotlinx.time)
    implementation(libs.kodein)

}