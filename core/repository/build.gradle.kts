plugins {
    id("patientapp.android.library")
}

android {
    namespace = "com.aya.digital.core.repository"
}

dependencies {
    implementation(project(":core:util"))
    implementation(project(":core:network"))
    implementation(project(":core:datasource"))
    implementation(project(":core:data"))
    implementation(project(":core:mappers"))
    implementation(libs.kodein.framework.androidx)
    implementation(libs.rxkotlin)
    implementation(libs.open.auth)

}