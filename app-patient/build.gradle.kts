
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
            firebaseAppDistribution {
                testers="ali@example.com, bri@example.com, cal@example.com"
            }
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
    implementation(project(":feature:rootcontainer"))
    implementation(project(":feature:bottomdialog"))
    implementation(project(":feature:auth:chooser"))
    implementation(project(":feature:auth:container"))
    implementation(project(":feature:auth:signin"))
    implementation(project(":feature:auth:signup"))
    implementation(project(":core:appbase"))
    implementation(project(":core:baseresources"))
    implementation(project(":core:dibase"))
    implementation(project(":core:di"))
    implementation(project(":core:datastore"))
    implementation(project(":core:ext"))
    implementation(project(":core:mvi"))
    implementation(project(":core:navigation"))
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
}