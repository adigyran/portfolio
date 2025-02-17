
import com.aya.digital.healthapp.AyaPatientBuildType
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("healthapp.android.application")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {

    lint{
        disable.add("Instantiatable")
    }

    defaultConfig {
        applicationId = "com.aya.digital.healthapp.doctor"
        versionCode = 16
        versionName = "0.0.16" // X.Y.Z; X = Major, Y = minor, Z = Patch level

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    val keystorePropertiesFile = rootProject.file("keystore_doctor.properties");
    val keystoreProperties = Properties()
    keystoreProperties.load(FileInputStream(keystorePropertiesFile))
    signingConfigs {
        create("release") {
            keyAlias = keystoreProperties.getProperty("keyAlias")
            keyPassword = keystoreProperties.getProperty("keyPassword")
            storeFile = file(keystoreProperties.getProperty("storeFile"))
            storePassword = keystoreProperties.getProperty("storePassword")
        }
    }

    buildTypes {
        val debug by getting {
            applicationIdSuffix = AyaPatientBuildType.DEBUG.applicationIdSuffix
            firebaseAppDistribution {
                serviceCredentialsFile = "./ayadoc-28b1b-e839ed480468.json"
                groups="general"
                releaseNotesFile = "${parent!!.projectDir}/releasenotes.txt"
            }
        }
        val release by getting {
            firebaseAppDistribution {
                serviceCredentialsFile = "./ayadoc-28b1b-e839ed480468.json"
                groups="general"
                releaseNotesFile = "${parent!!.projectDir}/releasenotes.txt"
            }
            signingConfig = signingConfigs.getByName("release")
        }
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }



    namespace = "com.aya.digital.healthapp.doctor"

}

dependencies {
    implementation(project(":feature:rootcontainer"))
    implementation(project(":feature:bottomdialogs:bottomdialog"))
    implementation(project(":feature:bottomdialogs:dateappointmentsdialog"))
    implementation(project(":feature:bottomdialogs:createscheduledialog"))
    implementation(project(":feature:bottomdialogs:codedialog"))
    implementation(project(":feature:bottomnavhost"))
    implementation(project(":feature:tabs:appointments"))
    implementation(project(":feature:tabviews:appointmentsscheduler"))
    implementation(project(":feature:tabs:profile"))
    implementation(project(":feature:tabviews:profile"))
    implementation(project(":feature:choosers:selectwithsearch"))
    implementation(project(":feature:auth:chooser"))
    implementation(project(":feature:auth:container"))
    implementation(project(":feature:auth:signin"))
    implementation(project(":feature:auth:signup"))
    implementation(project(":feature:auth:restorepassword"))
    implementation(project(":feature:choosers:selectwithsearch"))
    implementation(project(":feature:videocall:videocallscreen"))
    implementation(project(":feature:videocall:videocallservice"))
    implementation(project(":feature:videocall:videocallcontainer"))
    implementation(project(":feature:profile:security:securitysummary"))
    implementation(project(":feature:profile:security:changeemail"))
    implementation(project(":feature:profile:security:changepassword"))
    implementation(project(":feature:profile:generalinfo:edit"))
    implementation(project(":feature:profile:generalinfo:view"))
    implementation(project(":feature:profile:clinicinfo"))
    implementation(project(":feature:profile:address"))
    implementation(project(":feature:profile:insurance:doctorinsurance"))
    implementation(project(":feature:profile:prescriptions:edit"))
    implementation(project(":feature:profile:prescriptions:list"))
    implementation(project(":feature:profile:prescriptions:view"))
    implementation(project(":feature:appointments:appointmentcard"))
    implementation(project(":feature:profile:notifications"))
    implementation(project(":core:appbase"))
    implementation(project(":core:baseresources"))
    implementation(project(":core:dibase"))
    implementation(project(":core:data:base"))
    implementation(project(":core:ui:base"))
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
    debugImplementation(libs.leak.canary)
    implementation(libs.open.auth)
    implementation(libs.kotlinx.time)
}