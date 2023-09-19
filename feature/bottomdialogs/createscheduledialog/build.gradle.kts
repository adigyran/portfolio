plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.feature.bottomdialogs.createscheduledialog"
}

dependencies {
    implementation(project(":core:domain:schedule"))
    implementation(project(":core:domain:base"))
    implementation(project(":core:ui:delegates:features:appointments:appointmentsscheduler"))
    implementation(project(":core:ui:delegates:components:fields:name"))
    implementation(project(":core:ui:delegates:components:fields:selection"))
    implementation(project(":core:ui:delegates:components:fields:dropdown"))
    implementation(libs.kotlinx.time)
}