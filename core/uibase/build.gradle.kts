plugins {
    id("patientapp.android.library")
}

android {
    namespace = "com.aya.digital.core.uibase"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:baseresources"))
    implementation(project(":core:util"))
    implementation(project(":core:ext"))
    implementation(project(":core:util"))
    implementation(project(":core:dibase"))
    implementation(project(":core:navigation"))
    implementation(project(":core:mvi"))
    implementation(project(":core:appbase"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material.design)
    implementation(libs.androidx.recyclerview)
    implementation(libs.kodein.framework.androidx)
    implementation(libs.androidx.viewpager)
    implementation(libs.cicerone)
    implementation(libs.rxrelay)
    implementation(libs.adapter.delegates)
    implementation(libs.adapter.delegates.view.binding)

}
