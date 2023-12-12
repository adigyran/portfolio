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
    implementation(project(":core:database"))
    implementation(project(":core:navigation"))
    implementation(project(":core:util"))
    implementation(project(":core:mappers:appointment"))
    implementation(project(":core:mappers:doctors"))
    implementation(project(":core:mappers:preferences"))
    implementation(project(":core:mappers:profile"))
    implementation(project(":core:mappers:schedule"))
    implementation(project(":core:mappers:dictionaries"))
    implementation(project(":core:repository:auth"))
    implementation(project(":core:repository:dictionaries"))
    implementation(project(":core:repository:doctors"))
    implementation(project(":core:repository:appointment"))
    implementation(project(":core:repository:schedule"))
    implementation(project(":core:repository:progress"))
    implementation(project(":core:repository:profile"))
    implementation(project(":core:repository:location"))
    implementation(project(":core:repository:prescriptions"))
    implementation(project(":core:repository:address"))
    implementation(project(":core:domain:auth"))
    implementation(project(":core:domain:profile"))
    implementation(project(":core:domain:dictionaries"))
    implementation(project(":core:domain:appointment"))
    implementation(project(":core:domain:doctors"))
    implementation(project(":core:domain:schedule"))
    implementation(project(":core:domain:root"))
    implementation(project(":core:domain:home"))
    implementation(project(":core:domain:location"))
    implementation(project(":core:security"))
    implementation(libs.kodein)
    implementation(libs.glide)
    kapt(libs.glide.annotationprocessor)
    implementation(libs.retrofit)

}