
import com.android.build.gradle.internal.tasks.factory.dependsOn
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
        applicationId = "com.aya.digital.healthapp.patient"
        versionCode = 73
        versionName = "0.0.73" // X.Y.Z; X = Major, Y = minor, Z = Patch level

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    val keystorePropertiesFile = rootProject.file("keystore_patient.properties");
    val keystoreProperties =  Properties()
    keystoreProperties.load(FileInputStream(keystorePropertiesFile))
    signingConfigs {
        create("release") {
            keyAlias = keystoreProperties.getProperty("keyAlias")
            keyPassword = keystoreProperties.getProperty("keyPassword")
            storeFile = file(keystoreProperties.getProperty("storeFile"))
            storePassword = keystoreProperties.getProperty("storePassword")
        }
    }
   /* if (project.hasProperty("propertyfile") && project.hasProperty("key.store")) {
        val keystorePropertiesFile = rootProject.file(project.properties["propertyfile"]!!)
        val keystoreProperties = Properties()
        keystoreProperties.load(FileInputStream(keystorePropertiesFile))

        signingConfigs {
            create("release") {
                keyAlias = keystoreProperties.getProperty("key.alias")
                keyPassword = keystoreProperties.getProperty("key.alias.password")
                storeFile = file(project.properties["key.store"]!!)
                storePassword = keystoreProperties.getProperty("key.store.passord")
            }
        }
    }*/
    project.tasks.preBuild.dependsOn("copyNotesToAssets")
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
    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }


    namespace = "com.aya.digital.healthapp.patient"

}

tasks.register<Copy>(name = "copyNotesToAssets") {
    from(layout.buildDirectory.file("${parent!!.projectDir}/releasenotes.txt"))
    into(layout.buildDirectory.dir("${projectDir}/src/main/assets"))
}
dependencies {
    implementation(project(":feature:rootcontainer"))
    implementation(project(":feature:bottomdialogs:bottomdialog"))
    implementation(project(":feature:bottomdialogs:codedialog"))
    implementation(project(":feature:bottomdialogs:createappointmentdialog"))
    implementation(project(":feature:bottomdialogs:dateappointmentsdialog"))
    implementation(project(":feature:bottomdialogs:successappointmentdialog"))
    implementation(project(":feature:bottomdialogs:doctorsclusterlistdialog"))
    implementation(project(":feature:bottomnavhost"))
    implementation(project(":feature:tabs:appointments"))
    implementation(project(":feature:tabs:doctorsearch"))
    implementation(project(":feature:tabs:home"))
    implementation(project(":feature:tabviews:home"))
    implementation(project(":feature:tabviews:doctorsearchcontainer"))
    implementation(project(":feature:tabviews:appointments"))
    implementation(project(":feature:tabs:profile"))
    implementation(project(":feature:tabviews:profile"))
    implementation(project(":feature:profile:generalinfo:edit"))
    implementation(project(":feature:profile:generalinfo:view"))
    implementation(project(":feature:profile:insurance:add"))
    implementation(project(":feature:profile:insurance:list"))
    implementation(project(":feature:profile:security:securitysummary"))
    implementation(project(":feature:profile:security:changeemail"))
    implementation(project(":feature:profile:security:changephone"))
    implementation(project(":feature:profile:security:changepassword"))
    implementation(project(":feature:profile:emergencycontact"))
    implementation(project(":feature:profile:notifications"))
    implementation(project(":feature:profile:address"))
    implementation(project(":feature:doctors:doctorcard"))
    implementation(project(":feature:doctors:doctorssearch:doctorsearchlist"))
    implementation(project(":feature:doctors:doctorssearch:doctorsearchmap"))
    implementation(project(":feature:appointments:appointmentcard"))

    implementation(project(":feature:choosers:selectwithsearch"))
    implementation(project(":feature:auth:chooser"))
    implementation(project(":feature:auth:container"))
    implementation(project(":feature:auth:signin"))
    implementation(project(":feature:auth:signup"))
    implementation(project(":feature:auth:restorepassword"))

    implementation(project(":feature:videocall:videocallscreen"))
    implementation(project(":feature:videocall:videocallservice"))
    implementation(project(":feature:videocall:videocallcontainer"))

    implementation(project(":core:appbase"))
    implementation(project(":core:data:base"))
    implementation(project(":core:ui:base"))
    implementation(project(":core:baseresources"))
    implementation(project(":core:dibase"))
    implementation(project(":core:di"))
    implementation(project(":core:datastore"))
    implementation(project(":core:database"))
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
    implementation(libs.open.auth)
    implementation(libs.kotlinx.time)
    implementation(libs.google.places)
    implementation(libs.google.places.rx)
    implementation(libs.google.places.ktx)
    debugImplementation(libs.leak.canary)
}