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
    implementation(project(":core:ext"))
    implementation(project(":core:util"))
    implementation(project(":core:dibase"))
    implementation(project(":core:navigation"))
    implementation(project(":core:mvi"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material.design)
    implementation(libs.androidx.recyclerview)
    implementation(libs.kodein.framework.androidx)
    implementation(libs.androidx.viewpager)
    implementation(libs.mvi.mvicore)
    implementation(libs.mvi.mvicore.android)
    implementation(libs.mvi.mvicore.diff)
    implementation(libs.mvi.android.binder)
    implementation(libs.rxrelay)
    implementation(libs.adapter.delegates)
    implementation(libs.adapter.delegates.view.binding)
}
