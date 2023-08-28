plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.tabviews.appointmentsscheduler"
}

dependencies {
    implementation(project(":core:ext"))
    implementation(project(":core:domain:base"))
    implementation(project(":core:domain:appointment"))
    implementation(project(":core:domain:schedule"))
    implementation(project(":core:domain:profile"))
    implementation(project(":core:ui:delegates:features:appointments:appointmentsscheduler"))
    implementation(libs.kotlinx.time)
}