plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.repository"
}

dependencies {
    implementation(project(":core:util"))
    implementation(project(":core:networkbase"))
    implementation(project(":core:network"))
    implementation(project(":core:datasource"))
    implementation(project(":core:datastore"))
    implementation(project(":core:data"))
    implementation(project(":core:mappers"))
    implementation(libs.kodein)
    implementation(libs.jwt.decode)
    implementation(libs.rxkotlin)
    implementation(libs.open.auth)

}