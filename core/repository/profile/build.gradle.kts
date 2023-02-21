plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.repository.profile"
}

dependencies {
    implementation(project(":core:util"))
    implementation(project(":core:networkbase"))
    implementation(project(":core:network:main"))
    implementation(project(":core:datasource"))
    implementation(project(":core:datastore"))
    implementation(libs.kodein)
    implementation(libs.jwt.decode)
    implementation(libs.rxkotlin)
    implementation(libs.open.auth)
}