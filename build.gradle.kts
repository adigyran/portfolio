// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = uri("https://jitpack.io"))
        maven(url = uri("https://dl.bintray.com/guardian/android"))
        maven(url = uri("https://maven.google.com/"))
        maven(url = uri("https://maven.fabric.io/public"))
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.4.0")
        classpath("com.google.gms:google-services:4.3.10")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.8.1")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.secrets) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.dokka) apply true
    alias(libs.plugins.dependency.graph.generator) apply true
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
}

tasks.dokkaHtmlMultiModule {
    moduleName.set("Aya Dev HealthApp")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}