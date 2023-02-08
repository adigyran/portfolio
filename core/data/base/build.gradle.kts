plugins {
    id("healthapp.android.library")
    id("kotlin-parcelize")
}

android {
    namespace = "com.aya.digital.core.data.base"
}

dependencies {
    implementation(project(":core:network:model"))
}