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
include(":core:database")
include(":core:dibase")
//mappers
include(":core:mappers:appointment")
include(":core:mappers:doctors")
include(":core:mappers:preferences")
include(":core:mappers:profile")
include(":core:mappers:schedule")
include(":core:mappers:dictionaries")
//
include(":core:di")
//repository
include(":core:repository:auth")
include(":core:repository:profile")
include(":core:repository:appointment")
include(":core:repository:doctors")
include(":core:repository:dictionaries")
include(":core:repository:schedule")
include(":core:repository:progress")
include(":core:repository:location")

//data
include(":core:data:base")
include(":core:data:profile")
include(":core:data:appointment")
include(":core:data:schedule")
include(":core:data:doctors")
include(":core:data:preferences")
include(":core:data:dictionaries")
include(":core:data:progress")
include(":core:data:location")

//
include(":core:ext")
include(":core:model")
include(":core:navigation")
//network
include(":core:networkbase")
include(":core:network:model")
include(":core:network:api:services")
include(":core:network:main")
include(":core:network:interceptors")

include(":core:security")
//ui
include(":core:ui:adapters")
include(":core:ui:core")
include(":core:ui:base")
//util modules
include(":core:mvi")
include(":core:util")
include(":core:testing")
//domain
include(":core:domain:base")
include(":core:domain:appointment")
include(":core:domain:doctors")
include(":core:domain:profile")
include(":core:domain:auth")
include(":core:domain:dictionaries")
include(":core:domain:schedule")
include(":core:domain:root")
include(":core:domain:location")
include(":core:domain:home")

//ui delegates
//components
//fields
include(":core:ui:delegates:components:fields:emailphone")
include(":core:ui:delegates:components:fields:password")
include(":core:ui:delegates:components:fields:name")
include(":core:ui:delegates:components:fields:selection")
include(":core:ui:delegates:components:fields:validated")
include(":core:ui:delegates:components:fields:dropdown")
include(":core:ui:delegates:components:fields:validatednumber")

//labels
include(":core:ui:delegates:components:labels:headline")
include(":core:ui:delegates:components:labels:headlinetwoline")
include(":core:ui:delegates:components:labels:spannablehelper")
//buttons
include(":core:ui:delegates:components:buttons:filledbutton")
//features
//auth
include(":core:ui:delegates:features:auth:signin")
include(":core:ui:delegates:features:auth:signup")
include(":core:ui:delegates:features:auth:chooser")
//choosers
include(":core:ui:delegates:features:choosers:selectwithsearch")
//profile
include(":core:ui:delegates:features:profile:main")
include(":core:ui:delegates:features:profile:generalinfo")
include(":core:ui:delegates:features:profile:emergencycontactinfo")
include(":core:ui:delegates:features:profile:securitysummary")
include(":core:ui:delegates:features:profile:clinicinfo")
include(":core:ui:delegates:features:profile:insurance")
//appointments
include(":core:ui:delegates:features:appointments:patientappointment")
include(":core:ui:delegates:features:appointments:appointmentsscheduler")
//appointmentcard
include(":core:ui:delegates:features:appointmentcard:appointmentdetails")
//doctors
include(":core:ui:delegates:features:doctors:doctoritem")
//doctorcard
include(":core:ui:delegates:features:doctorcard:doctorslot")
include(":core:ui:delegates:features:doctorcard:doctordetails")
//home
include(":core:ui:delegates:features:home:homeitems")

//tabs
include(":feature:tabs:home")
include(":feature:tabs:appointments")
include(":feature:tabs:profile")
include(":feature:tabs:doctorsearch")
//tabviews
include(":feature:tabviews:home")
include(":feature:tabviews:profile")
include(":feature:tabviews:doctorsearchcontainer")
include(":feature:tabviews:appointments")
include(":feature:tabviews:appointmentsscheduler")

//containers
include(":feature:rootcontainer")
include(":feature:bottomnavhost")
//dialogs
include(":feature:bottomdialogs:bottomdialog")
include(":feature:bottomdialogs:codedialog")
include(":feature:bottomdialogs:createappointmentdialog")
include(":feature:bottomdialogs:dateappointmentsdialog")
include(":feature:bottomdialogs:successappointmentdialog")
include(":feature:bottomdialogs:doctorsclusterlistdialog")
include(":feature:bottomdialogs:createscheduledialog")

//auth
include(":feature:auth:chooser")
include(":feature:auth:signup")
include(":feature:auth:signin")
include(":feature:auth:restorepassword")
include(":feature:auth:container")
//choosers
include(":feature:choosers:selectwithsearch")
//profile
include(":feature:profile:notifications")
include(":feature:profile:emergencycontact")
include(":feature:profile:generalinfo:edit")
include(":feature:profile:generalinfo:view")
include(":feature:profile:insurance:add")
include(":feature:profile:insurance:list")
include(":feature:profile:insurance:doctorinsurance")
include(":feature:profile:clinicinfo")
include(":feature:profile:security:securitysummary")
include(":feature:profile:security:changepassword")
include(":feature:profile:security:changeemail")
include(":feature:profile:security:changephone")

//doctors
include(":feature:doctors:doctorcard")
//search
include(":feature:doctors:doctorsfiltersview")
include(":feature:doctors:doctorssearch:doctorsearchlist")
include(":feature:doctors:doctorssearch:doctorsearchmap")
//appointments
include(":feature:appointments:appointmentcard")
//videocall
include(":feature:videocall:videocallscreen")

include(":core:localisation")


