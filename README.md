# Development Environment

**Aya Health** uses the Gradle build system and can be imported directly into Android Studio (make sure you are using the latest stable version available [here](https://developer.android.com/studio)).

It uses gradle 8.4, it has gradlew properties, so the required gradle should init automatically



# Architecture

The **Aya Health** app follows the
[official architecture guidance](https://developer.android.com/topic/architecture)
and is described in detail in the
[architecture learning journey](docs/ArchitectureLearningJourney.md).

# Modularization

The **Aya Health** app has been fully modularized and you can find the detailed guidance and
description of the modularization strategy used in
[modularization learning journey](docs/ModularizationLearningJourney.md).

Project consist of:
1. Two apps [`app-doctor`](app-doctor), [`app-patient`](app-patient)
2. Core modules in [`core`](core)
3. Feature modules [`feature`](core)

Please use provided guide to avoid reversing dependency tree

# Build

Dependencies managed by [gradle version catalog](https://docs.gradle.org/current/userguide/platforms.html) and uses [toml file](gradle/libs.versions.toml)

The [`build-logic`](build-logic) folder defines project-specific convention plugins, used to keep a single
source of truth for common module configurations.

The app uses
[product flavors](https://developer.android.com/studio/build/build-variants#product-flavors) to
control where content for the app should be loaded from.

To build Doctor app:
./gradlew :app-doctor:assembleDemoRelease - for release apk
./gradlew :app-doctor:assembleDemoDebug - for debug apk

To build Patient app

./gradlew :app-patient:assembleDemoRelease - for release apk
./gradlew :app-patient:assembleDemoDebug - for debug apk

This will create apks of flavours in build\outputs\apk

# DI
The **Aya Health** app uses [Kodein](https://github.com/kosi-libs/Kodein) as DI framework 

# Authorization
Authorization is based on Auth 2.0 PKCE protocol which is performed by the means of OpenId's AppAuth library.
On initial launch of the application an access token remaining life time is assessed if it is at least one hour left, otherwise relogin WebView is shown. (On the new app architecture it does not matter)
## Doctor / Patient navigation flow
HealthApp uses two separate apps for patient and doctor
[`app-doctor`](app-doctor) - for Doctor flow\
[`app-patient`](app-patient) - for Patient flow

App contains base info about navigation and such, all screens including root should be placed in `feature` as module

# Presentation
The **Aya Health** app uses OrbitMVI as presentation architecture 
# Networking
For networking requests OkHttp is used in conjunction with Retrofit.

# Signing and API keys
Project should contain a local.proprties files with following content

MAPS_API_KEY=
KEYSTORE_FILE=
KEYSTORE_PASSWORD=
KEYSTORE_KEY_ALIAS=
KEYSTORE_KEY_PASSWORD=

# Shared Preferences
Shared preferences are used to store tokens and several flags on whether a user has seen disclaimer and onboarding screens.

