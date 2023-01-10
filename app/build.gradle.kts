plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = ConfigData.compileSdkVersion
    buildToolsVersion = ConfigData.buildToolsVersion

    defaultConfig {
        applicationId = "com.aya.digital.patientapp"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {
    implementation(Deps.kotlin)
    implementation(Deps.appCompat)
    implementation(Deps.materialDesign)
    implementation(Deps.timber)
    implementation(Deps.constraintLayout)
    androidTestImplementation(Deps.testCore)
    androidTestImplementation(Deps.espresso)
    androidTestImplementation(Deps.jUnitRules)
    androidTestImplementation(Deps.jUnitRunner)
    androidTestImplementation(Deps.extJUnit)
    androidTestImplementation(Deps.extTruth)

}