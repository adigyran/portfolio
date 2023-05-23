
plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.database"
}


dependencies {
    implementation(project(":core:util"))
    implementation(project(":core:security"))
    implementation(project(":core:data:preferences"))
    implementation(project(":core:data:doctors"))
    implementation(libs.room.runtime)
    implementation(libs.room.rxjava3)
    kapt(libs.room.kapt)
    implementation(libs.kodein)
    implementation(libs.tink)
    implementation(libs.timber)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.rxjava3)

}