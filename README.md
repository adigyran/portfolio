# Development Environment

**Aya Health** uses the Gradle build system and can be imported directly into Android Studio (make sure you are using the latest stable version available [here](https://developer.android.com/studio)).

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

# DI
The **Aya Health** app uses Kodein as DI framework [kodein library](https://github.com/kosi-libs/Kodein)

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
# Interactors and converters
There are two different date formats used on “Old” backend which are yyyy-MM-dd'T'HH:mm:ss and MM/dd/yyyy.

Be careful not to use logging on release builds.
# Paging

# Signing and API keys
Project should contain a local.proprties files with following content

MAPS_API_KEY=
KEYSTORE_FILE=
KEYSTORE_PASSWORD=
KEYSTORE_KEY_ALIAS=
KEYSTORE_KEY_PASSWORD=

# Shared Preferences
Shared preferences are used to store tokens and several flags on whether a user has seen disclaimer and onboarding screens.

# Beware
* On the “Old” backend there are very tricky calls on updating profile/practitioner/patient data. There can be multiple calls, mostly PUT not PATCH calls.
* Onboarding screens should be tested against small screen devices. Layouts needs to be adapted.
* Doctors are requested ALL at once to be able to show something on the MAP and use filtering as separate functionality is not provided on “Old” backend.
* A calendar library used in the project reuses the RecyclerView/ViewHolder functionality and sometimes scrolls on month do not update the item state. (Might be a good idea to use another library, or idk)
* Schedules are loading for like 1 minute as of the time of writing this documentation.
* Async image loading does not necessarily follow constraints that you set on ImageView and alike.
