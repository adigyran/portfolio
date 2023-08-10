plugins {
    id("healthapp.android.feature")
}

android {
    namespace = "com.aya.digital.core.feature.profile.emergencycontact"
}

dependencies {
    implementation(project(":core:domain:profile"))
    implementation(project(":core:ui:delegates:features:profile:emergencycontactinfo"))
    implementation(project(":core:ui:delegates:components:fields:name"))
    implementation(project(":core:ui:delegates:components:fields:emailphone"))
    implementation(project(":core:ui:delegates:components:fields:validated"))

}