plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.repository.address"
}

dependencies {
    implementation(project(":core:util"))
    implementation(project(":core:ext"))
    implementation(project(":core:navigation"))
    implementation(project(":core:networkbase"))
    implementation(project(":core:network:model"))
    implementation(project(":core:network:main"))
    implementation(project(":core:datasource"))
    implementation(project(":core:datastore"))
    implementation(project(":core:data:profile"))
    implementation(project(":core:data:base"))
    implementation(project(":core:data:location"))
    implementation(libs.rxandroid)
    implementation(libs.kodein)
    implementation(libs.jwt.decode)
    implementation(libs.rxkotlin)
    implementation(libs.open.auth)
    implementation(libs.ktx.maps)
    implementation(libs.google.play.location)
    implementation(libs.gms.maps)
    implementation(libs.gms.maps.utils)
    implementation(libs.rx.maps)
    implementation(libs.google.places)
    implementation(libs.google.places.ktx)

}