val ktlintVersion = "0.43.0"

initscript {
    val spotlessVersion = "6.12.1"

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("com.diffplug.spotless:spotless-plugin-gradle:$spotlessVersion")
    }
}

rootProject {
    subprojects {
        apply<com.diffplug.gradle.spotless.SpotlessPlugin>()
        extensions.configure<com.diffplug.gradle.spotless.SpotlessExtension> {
            kotlin {
                target("**/*.kt")
                targetExclude("**/build/**/*.kt")
                ktlint(ktlintVersion).userData(mapOf("android" to "true"))
                licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
            }
            format("kts") {
                target("**/*.kts")
                targetExclude("**/build/**/*.kts")
                // Look for the first line that doesn't have a block comment (assumed to be the license)
                licenseHeaderFile(rootProject.file("spotless/copyright.kts"), "(^(?![\\/ ]\\*).*$)")
            }
            format("xml") {
                target("**/*.xml")
                targetExclude("**/build/**/*.xml")
                // Look for the first XML tag that isn't a comment (<!--) or the xml declaration (<?xml)
                licenseHeaderFile(rootProject.file("spotless/copyright.xml"), "(<[^!?])")
            }
        }
    }
}

/*    //testing
    val jUnit by lazy { "junit:junit:${Versions.jUnit}" }
    val testCore by lazy {"androidx.test:core:${Versions.testCore}"}
    val espresso by lazy {"androidx.test.espresso:espresso-core:${Versions.espressoCore}"}
    val jUnitRunner by lazy {"androidx.test:runner:${Versions.testRunner}"}
    val jUnitRules by lazy {"androidx.test:rules:${Versions.testRules}"}
    val extJUnit by lazy {"androidx.test.ext:junit:${Versions.testJunit}"}
    val extTruth by lazy {"androidx.test.ext:truth:${Versions.testTruth}"}
*/