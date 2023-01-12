package com.aya.digital.core.uibase.core

import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.aya.digital.core.util.keyboardheighprovider.KeyboardHeightProvider
import com.aya.digital.core.util.retainedinstancemanager.IHasRetainedInstance
import com.aya.digital.core.util.retainedinstancemanager.IdProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.kodein.di.DI
import java.util.*

abstract class CoreActivity<Binding : ViewBinding> : AppCompatActivity(),
    IHasRetainedInstance<DI>, IdProvider {
    private lateinit var keyboardHeightProvider: KeyboardHeightProvider

    var keyboardHeight: Int = 0

    protected open val activityResultManager: ActivityResultManager? = null
    protected open val permissionManager: PermissionsManager? = null

    protected val disposables: CompositeDisposable = CompositeDisposable()

    protected lateinit var binding: Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(CoreActivity::class.java.name, "onCreate: ${this.javaClass.name}")
        this.intent.putExtra(
            CONTAINER_UUID, savedInstanceState?.getString(
                CONTAINER_UUID
            ) ?: UUID.randomUUID().toString()
        )

        initDi()

        super.onCreate(savedInstanceState)

        binding = provideViewBinding(layoutInflater)
        val view = binding.root
        setContentView(view)

        keyboardHeightProvider = KeyboardHeightProvider(this)

        // make sure to start the keyboard height provider after the onResume
        // of this activity. This is because a popup window must be initialised
        // and attached to the activity root view.
        findViewById<ViewGroup>(android.R.id.content).getChildAt(0)
            .post { keyboardHeightProvider.start() }


        permissionManager?.requestObservable?.subscribe {
            requestPermissions(it.second, it.first)
        }?.also { disposables.add(it) }

        Log.d(CoreActivity::class.java.name,
            "onCreate: $activityResultManager ${activityResultManager?.startActivityForResultSubject}")
        activityResultManager?.startActivityForResultSubject?.subscribe {
            Log.d(CoreActivity::class.java.name,
                "onCreate: startActivityForResultSubject ${this.javaClass.name}")
            startActivityForResult(it.intent, it.requestCode, it.options)
        }?.also { disposables.add(it) }

        activityResultManager?.resolveExceptionSubject?.subscribe {
            try {
                // Show the dialog by calling startResolutionForResult(),
                // and check the result in onActivityResult().
                it.exception.startResolutionForResult(this, it.requestCode)
            } catch (sendEx: IntentSender.SendIntentException) {
                // Ignore the error.
            }
        }?.also { disposables.add(it) }

        prepareUi()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (permissionManager != null) {
            val list = PermissionsManager.getPermissionList(this, permissions, grantResults)
            permissionManager!!.onRequestResult(requestCode, list)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activityResultManager?.onActivityResult(requestCode, resultCode, data)
    }

    abstract fun provideViewBinding(inflater: LayoutInflater): Binding

    open fun prepareUi() = Unit

    open fun initDi() = Unit

    override fun onPause() {
        super.onPause()
        keyboardHeightProvider.setKeyboardHeightObserver(null)
    }

    override fun onResume() {
        super.onResume()
        keyboardHeightProvider.setKeyboardHeightObserver { height, orientation ->
            keyboardHeight = height
        }
    }

    override fun onStop() {
        Log.d(CoreActivity::class.java.name, "onStop: ${this.javaClass.name}")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(CoreActivity::class.java.name, "onDestroy: ${this.javaClass.name}")
        disposables.clear()

        super.onDestroy()
        keyboardHeightProvider.close()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(
            CONTAINER_UUID, this.intent.getStringExtra(
                CONTAINER_UUID
            )
        )
        super.onSaveInstanceState(outState)
    }

    final override fun getUuid(): String = this.intent.getStringExtra(
        CONTAINER_UUID
    )!!

    companion object {
        private val CONTAINER_UUID = "${CoreActivity::class.java.canonicalName}.CONTAINER_UUID"
    }
}