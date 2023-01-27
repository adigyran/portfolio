// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
   // alias(libs.plugins.gradle.plugin) apply true
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.secrets) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.dokka) apply true
    alias(libs.plugins.dependency.graph.generator) apply true
    alias(libs.plugins.gms.googleServices) apply false
}

tasks.dokkaHtmlMultiModule {
    moduleName.set("Aya Dev HealthApp")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}