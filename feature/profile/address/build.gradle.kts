plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.profile.address"
}

dependencies {
    implementation(project(":core:domain:profile"))
    implementation(project(":core:ui:delegates:features:profile:address"))
    implementation(libs.gms.maps)
    implementation(libs.gms.maps.utils)
    implementation(libs.google.play.location)
    implementation(libs.rx.maps)
    implementation(libs.ktx.maps)
    implementation(libs.rxandroid)

}