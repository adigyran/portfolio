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
//mappers
include(":core:mappers:appointment")
include(":core:mappers:doctors")
include(":core:mappers:preferences")
include(":core:mappers:profile")
include(":core:mappers:schedule")
//
include(":core:di")
//repository
include(":core:repository:auth")
include(":core:repository:profile")
include(":core:repository:appointment")
include(":core:repository:doctors")
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
include(":core:ui:delegates:fields:emailphone")
include(":core:ui:delegates:fields:password")
include(":core:ui:delegates:fields:name")
include(":core:ui:delegates:fields:selection")
include(":core:ui:delegates:fields:validated")

include(":core:ui:delegates:buttons:filledbutton")
include(":core:ui:delegates:features:auth:signin")
include(":core:ui:delegates:features:auth:signup")
include(":core:ui:delegates:features:auth:chooser")
include(":core:ui:delegates:features:profile")
include(":core:mvi")
include(":core:util")
include(":core:testing")
//domain
include(":core:domain:appointment")
include(":core:domain:doctors")
include(":core:domain:profile")
include(":core:domain:auth")
//features
//containers
include(":feature:rootcontainer")
include(":feature:bottomnavhost")
//
include(":feature:bottomdialog")
//auth
include(":feature:auth:chooser")
include(":feature:auth:signup")
include(":feature:auth:signin")
include(":feature:auth:container")

include(":localisation")


