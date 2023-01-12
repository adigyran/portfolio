package com.aya.digital.core.dibase.scopes

import android.app.Application
import com.aya.digital.core.util.retainedinstancemanager.helpers.ActivityLifecycleHelper

object CustomScopesManager {
    fun init(app: Application) = app.registerActivityLifecycleCallbacks(
        ActivityLifecycleHelper(
            onActivityDestroyed = { activity -> CustomActivityScope.clearScope(activity) },
            onFragmentDestroyed = { fragment -> CustomFragmentScope.clearScope(fragment) }
        )
    )
}