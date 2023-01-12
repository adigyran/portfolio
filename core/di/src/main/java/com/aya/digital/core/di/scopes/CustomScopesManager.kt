package com.aya.digital.core.di.scopes

import android.app.Application
import ru.ivan.core.util.retainedinstancemanager.helpers.ActivityLifecycleHelper

object CustomScopesManager {
    fun init(app: Application) = app.registerActivityLifecycleCallbacks(
        ActivityLifecycleHelper(
            onActivityDestroyed = { activity -> CustomActivityScope.clearScope(activity) },
            onFragmentDestroyed = { fragment -> CustomFragmentScope.clearScope(fragment) }
        )
    )
}