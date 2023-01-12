plugins {
    id("patientapp.android.library")
}

android {
    namespace = "com.aya.digital.core.util"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.kodein.framework.androidx)
}