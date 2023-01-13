plugins {
    id("patientapp.android.library")
}

android {
    namespace = "com.aya.digital.core.datasources"
}

dependencies {

    implementation(project(":core:dibase"))
    implementation(project(":core:network"))
    implementation(libs.rxkotlin)
    implementation(libs.kotlinx.time)
    implementation(libs.kodein.framework.androidx)
    implementation(libs.retrofit)

}