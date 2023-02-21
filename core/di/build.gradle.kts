plugins {
    id("healthapp.android.library")
}

android {
    namespace = "com.aya.digital.core.di"
}

dependencies {
    implementation(project(":core:dibase"))
    implementation(project(":core:network:main"))
    implementation(project(":core:datasource"))
    implementation(project(":core:datastore"))
    implementation(project(":core:navigation"))
    implementation(project(":core:mappers:appointment"))
    implementation(project(":core:mappers:doctors"))
    implementation(project(":core:mappers:preferences"))
    implementation(project(":core:mappers:profile"))
    implementation(project(":core:mappers:schedule"))
    implementation(project(":core:security"))
    implementation(libs.kodein)
}