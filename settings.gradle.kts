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
    }
}

rootProject.name = "patientapp"
include(":app")
include(":core:designsystem")
include(":core:di")
include(":core:ext")
include(":core:navigation")
include(":core:network")
include(":core:uibase")

