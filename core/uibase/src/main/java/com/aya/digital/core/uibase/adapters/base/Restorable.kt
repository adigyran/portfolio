package com.aya.digital.core.uibase.adapters.base

import android.os.Bundle

interface Restorable {
    fun onSaveInstanceState(outState: Bundle)

    fun onViewStateRestored(savedInstanceState: Bundle?)
}