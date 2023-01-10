
import com.aya.digital.patientapp.AyaPatientBuildType

plugins {
    id("patientapp.android.application")
}

android {


    defaultConfig {
        applicationId = "com.aya.digital.patientapp"
        versionCode = 1
        versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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

    namespace = "com.aya.digital.patientapp"

}

dependencies {
/*    implementation(Deps.kotlin)
    implementation(Deps.appCompat)
    implementation(Deps.materialDesign)
    implementation(Deps.timber)
    implementation(Deps.constraintLayout)
    testImplementation(Deps.jUnit)
    androidTestImplementation(Deps.testCore)
    androidTestImplementation(Deps.espresso)
    androidTestImplementation(Deps.jUnitRules)
    androidTestImplementation(Deps.jUnitRunner)
    androidTestImplementation(Deps.extJUnit)
    androidTestImplementation(Deps.extTruth)*/
}