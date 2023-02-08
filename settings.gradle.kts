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
//apps
include(":app-patient")
include(":app-doctor")
//core
include(":core:appbase")
include(":core:baseresources")
include(":core:designsystem")
include(":core:datasource")
include(":core:datastore")
include(":core:dibase")
include(":core:domain")
include(":core:repository")
//mappers
include(":core:mappers:appointment")
include(":core:mappers:doctors")
include(":core:mappers:preferences")
include(":core:mappers:profile")
include(":core:mappers:schedule")
//
include(":core:di")
//data
include(":core:data:base")
include(":core:data:profile")
include(":core:data:appointment")
include(":core:data:schedule")
include(":core:data:doctors")
include(":core:data:preferences")
//
include(":core:ext")
include(":core:model")
include(":core:navigation")
//network
include(":core:networkbase")
include(":core:network:model")
include(":core:network:api:services")
include(":core:network:main")
include(":core:security")
//ui
include(":core:ui:adapters")
include(":core:ui:core")
include(":core:ui:base")
//ui delegates
include(":core:ui:delegates:auth:signin")
include(":core:ui:delegates:auth:signup")
include(":core:ui:delegates:auth:chooser")
include(":core:ui:delegates:profile")
include(":core:mvi")
include(":core:util")
include(":core:testing")
//features
//containers
include(":feature:rootcontainer")
include(":feature:bottomnavhost")
//
include(":feature:bottomdialog")
//auth
include(":feature:auth:chooser")
include(":feature:auth:signup")
include(":feature:auth:signin:patient")
include(":feature:auth:signin:doctor")
include(":feature:auth:container")

