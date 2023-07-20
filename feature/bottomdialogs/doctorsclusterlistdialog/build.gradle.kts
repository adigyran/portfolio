plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog"
}

dependencies {
    implementation(project(":core:domain:doctors"))
    implementation(project(":core:domain:base"))
    implementation(project(":core:ui:delegates:features:doctors:doctoritem"))
    implementation(libs.kotlinx.time)
}