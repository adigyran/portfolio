plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.repository.location"
}

dependencies {
    implementation(project(":core:util"))
    implementation(project(":core:ext"))
    implementation(project(":core:networkbase"))
    implementation(project(":core:network:model"))
    implementation(project(":core:network:main"))
    implementation(project(":core:datasource"))
    implementation(project(":core:datastore"))
    implementation(project(":core:data:base"))
    implementation(project(":core:data:location"))


    implementation(libs.kodein)
    implementation(libs.jwt.decode)
    implementation(libs.rxkotlin)
    implementation(libs.google.play.location)
}