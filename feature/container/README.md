# Authorization
Authorization is based on Auth 2.0 PKCE protocol which is performed by the means of OpenId's AppAuth library.
On initial launch of the application an access token remaining life time is assessed if it is at least one hour left, otherwise relogin WebView is shown. (On the new app architecture it does not matter)
## Doctor / Patient navigation flow
The navigation graphs are decided upon ROLE_PRACTITIONER field in Access Token, which can be either ROLE_PRACTITIONER or ROLE_PATIENT as of the day of writing this update. In Presence of both roles, ROLE_PRACTITIONER.
## Beware
A google's navigation library is used. The only solution that worked in switching Patient's graph to Doctor's was a relaunch of the activity. Manually switching of graphs sometimes results in error.
# MVC
For views moderation an MVVM architecture is used. Google's ViewModels are used with LiveData, InputField and AppList are helper classes with livedatas for all the common properties. The bindTo helper functions are created to bind the previous ones to views.
# Networking
For networking requests OkHttp is used in conjunction with Retrofit.
# Interactors and converters
There are two different date formats used on “Old” backend which are yyyy-MM-dd'T'HH:mm:ss and MM/dd/yyyy.

Be careful not to use logging on release builds.
# Paging
A custom paging class is created to secure the manipulation on page incrementation. Be careful not to forget cleaning the results of pagination on refreshing.
# Compose UI
Compose UI is to be found in some places of the app as a more rapid solution to complex UI requests. Beware that the compose state is retained on navigation unlike ViewModels(if not specifically made to. So the correct implementation is to use derivedStateOf on ViewModel's state and not self hoisted.
# Signing and API keys
Project should contain a local.proprties files with following content

MAPS_API_KEY=
KEYSTORE_FILE=
KEYSTORE_PASSWORD=
KEYSTORE_KEY_ALIAS=
KEYSTORE_KEY_PASSWORD=

# Shared Preferences
Shared preferences are used to store tokens and several flags on whether a user has seen disclaimer and onboarding screens.
# Helper viewmodel classes
There is an AppList and an InputField classes which contain many livedatas reflecting various characteristics of the List and InputField respectively. Eg AppList contains isRefreshing, isLoadingMore, isErrorViewVisible, isEmptyViewVisible. They can be very handy to reuse also the linking functions are also used by calling bindTo. The whole livedata can be replaced with simple state thought.
Text class helps to add a class to viewmodel which either shows response from backend or a Resource string.
# Beware
* On the “Old” backend there are very tricky calls on updating profile/practitioner/patient data. There can be multiple calls, mostly PUT not PATCH calls.
* Onboarding screens should be tested against small screen devices. Layouts needs to be adapted.
* Doctors are requested ALL at once to be able to show something on the MAP and use filtering as separate functionality is not provided on “Old” backend.
* A calendar library used in the project reuses the RecyclerView/ViewHolder functionality and sometimes scrolls on month do not update the item state. (Might be a good idea to use another library, or idk)
* Schedules are loading for like 1 minute as of the time of writing this documentation.
* Async image loading does not necessarily follow constraints that you set on ImageView and alike.
