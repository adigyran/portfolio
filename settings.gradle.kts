pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = uri("https://jitpack.io"))
        maven(url = uri("https://dl.bintray.com/guardian/android"))
        maven(url = uri("https://maven.google.com/"))
        maven(url = uri("https://maven.fabric.io/public"))
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = uri("https://jitpack.io"))
        maven(url = uri("https://dl.bintray.com/guardian/android"))
        maven(url = uri("https://maven.google.com/"))
        maven(url = uri("https://maven.fabric.io/public"))
    }
}

rootProject.name = "healthapp"
include(":app-patient")
include(":app-doctor")
include(":core:appbase")
include(":core:baseresources")
include(":core:designsystem")
include(":core:datasource")
include(":core:datastore")
include(":core:dibase")
include(":core:domain")
include(":core:mappers")
include(":core:di")
include(":core:data")
include(":core:ext")
include(":core:model")
include(":core:navigation")
include(":core:network")
include(":core:repository")
include(":core:security")
include(":core:uibase")
include(":core:mvi")
include(":core:util")
include(":core:networkbase")
include(":core:ui")
include(":core:testing")
include(":feature:container")
include(":feature:bottomdialog")





