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
    implementation(project(":core:util"))
    implementation(project(":core:mappers:appointment"))
    implementation(project(":core:mappers:doctors"))
    implementation(project(":core:mappers:preferences"))
    implementation(project(":core:mappers:profile"))
    implementation(project(":core:mappers:schedule"))
    implementation(project(":core:mappers:dictionaries"))
    implementation(project(":core:repository:auth"))
    implementation(project(":core:repository:dictionaries"))
    implementation(project(":core:domain:auth"))
    implementation(project(":core:repository:profile"))
    implementation(project(":core:domain:profile"))
    implementation(project(":core:domain:dictionaries"))
    implementation(project(":core:domain:appointment"))
    implementation(project(":core:domain:doctors"))
    implementation(project(":core:security"))
    implementation(libs.kodein)
    implementation(libs.glide)
    kapt(libs.glide.annotationprocessor)
    implementation(libs.retrofit)

}