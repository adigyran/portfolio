plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.datasource"
}

dependencies {

    implementation(project(":core:dibase"))
    implementation(project(":core:network"))
    implementation(libs.rxkotlin)
    implementation(libs.kotlinx.time)
    implementation(libs.kodein)
    implementation(libs.retrofit)

}