
import com.aya.digital.healthapp.AyaPatientBuildType

plugins {
    id("healthapp.android.application")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {


    defaultConfig {
        applicationId = "com.aya.digital.healthapp.patient"
        versionCode = 1
        versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    buildTypes {
        val debug by getting {
            applicationIdSuffix = AyaPatientBuildType.DEBUG.applicationIdSuffix
        }
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }



    namespace = "com.aya.digital.healthapp.patient"

}

dependencies {
    implementation(project(":feature:container"))

    implementation(project(":core:appbase"))
    implementation(project(":core:baseresources"))
    implementation(project(":core:dibase"))
    implementation(project(":core:di"))
    implementation(project(":core:data"))
    implementation(project(":core:datastore"))
    implementation(project(":core:ext"))
    implementation(project(":core:mvi"))
    implementation(project(":core:navigation"))
    implementation(project(":core:uibase"))
    implementation(project(":core:util"))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.rxkotlin)
    implementation(libs.rxandroid)
    implementation(libs.material.design)
    implementation(libs.kodein.framework.androidx)
    implementation(libs.cicerone)
    implementation(libs.eventbus)
    implementation(libs.timber)
    implementation("com.google.firebase:firebase-crashlytics:18.2.9")
    implementation("com.google.firebase:firebase-analytics:20.1.2")
}