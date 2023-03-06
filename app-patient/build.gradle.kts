
import com.aya.digital.healthapp.AyaPatientBuildType

plugins {
    id("healthapp.android.application")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {


    defaultConfig {
        applicationId = "com.aya.digital.healthapp.patient"
        versionCode = 4
        versionName = "0.0.4" // X.Y.Z; X = Major, Y = minor, Z = Patch level

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
                groups="general"
                releaseNotesFile = "${parent!!.projectDir}/releasenotes.txt"
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
    implementation(project(":feature:bottomdialogs:bottomdialog"))
    implementation(project(":feature:bottomdialogs:codedialog"))
    implementation(project(":feature:bottomnavhost"))
    implementation(project(":feature:tabs:appointments"))
    implementation(project(":feature:tabs:doctorsearch"))
    implementation(project(":feature:tabs:home"))
    implementation(project(":feature:tabviews:home"))
    implementation(project(":feature:tabs:profile"))
    implementation(project(":feature:tabviews:profile"))
    implementation(project(":feature:profile:generalinfo:edit"))
    implementation(project(":feature:profile:generalinfo:view"))
    implementation(project(":feature:profile:insurance:add"))
    implementation(project(":feature:profile:insurance:list"))
    implementation(project(":feature:profile:security:securitysummary"))
    implementation(project(":feature:profile:security:changeemailphone"))
    implementation(project(":feature:profile:security:changepassword"))
    implementation(project(":feature:profile:emergencycontact"))
    implementation(project(":feature:profile:notifications"))

    implementation(project(":feature:choosers:multiselect"))
    implementation(project(":feature:auth:chooser"))
    implementation(project(":feature:auth:container"))
    implementation(project(":feature:auth:signin"))
    implementation(project(":feature:auth:signup"))

    implementation(project(":core:appbase"))
    implementation(project(":core:data:base"))
    implementation(project(":core:ui:base"))
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