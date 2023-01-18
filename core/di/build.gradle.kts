plugins {
    id("patientapp.android.library")
}

android {
    namespace = "com.aya.digital.core.di"
}

dependencies {
    implementation(project(":core:dibase"))
    implementation(project(":core:network"))
    implementation(project(":core:datasource"))
    implementation(project(":core:datastore"))
    implementation(project(":core:data"))
    implementation(project(":core:mappers"))
    implementation(project(":core:repository"))
    implementation(libs.kodein.framework.androidx)
}