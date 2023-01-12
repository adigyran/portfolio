pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = uri("https://jitpack.io"))
        maven(url = uri("https://dl.bintray.com/guardian/android"))
        maven(url = uri("https://maven.google.com/"))
        maven(url = uri("https://maven.fabric.io/public"))
    }
}

rootProject.name = "patientapp"
include(":app")
include(":core:designsystem")
include(":core:dibase")
include(":core:di")
include(":core:ext")
include(":core:navigation")
include(":core:network")
include(":core:uibase")
include(":core:mvi")
include(":core:util")
include(":core:networkbase")
include(":core:ui")
include(":core:testing")


