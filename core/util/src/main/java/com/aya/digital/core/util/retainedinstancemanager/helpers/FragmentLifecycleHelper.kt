package com.aya.digital.core.util.retainedinstancemanager.helpers

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.util.*
import kotlin.collections.HashSet

internal class FragmentLifecycleHelper(
    private val onFragmentDestroyed: ((fragment: Fragment) -> Unit)?,
) :
    FragmentManager.FragmentLifecycleCallbacks() {
    private val fragmentsSavedInstances: MutableSet<String> = HashSet()

    override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
        val args = f.arguments

        when {
            args == null -> {
                val bundle = Bundle()
                bundle.putString(CONTAINER_UUID, UUID.randomUUID().toString())
                f.arguments = bundle
            }
            args.getString(CONTAINER_UUID) == null -> args.putString(
                CONTAINER_UUID, UUID.randomUUID().toString()
            )
        }
        super.onFragmentPreAttached(fm, f, context)
    }

    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
        super.onFragmentStarted(fm, f)
        fragmentsSavedInstances.remove(f.requireArguments().getString(CONTAINER_UUID)!!)
    }

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        super.onFragmentResumed(fm, f)
        fragmentsSavedInstances.remove(f.requireArguments().getString(CONTAINER_UUID)!!)
    }

    override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) {
        super.onFragmentSaveInstanceState(fm, f, outState)
        fragmentsSavedInstances.add(f.requireArguments().getString(CONTAINER_UUID)!!)
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        super.onFragmentDestroyed(fm, f)

        val uuid = f.requireArguments().getString(CONTAINER_UUID)!!

        if (f.requireActivity().isFinishing) {
            onFragmentDestroyed?.invoke(f)
            return
        }

        if (isPrincipalFinishing(f, fragmentsSavedInstances.contains(uuid))) {
            onFragmentDestroyed?.invoke(f)
        }

        fragmentsSavedInstances.remove(uuid)
    }

    private fun isPrincipalFinishing(fragment: Fragment, wasInstanceStateSaved: Boolean) =
        fragment.requireActivity().isFinishing || (!wasInstanceStateSaved // See http://stackoverflow.com/questions/34649126/fragment-back-stack-and-isremoving
                && (fragment.isRemoving || isAnyParentOfFragmentRemoving(fragment)))

    private fun isAnyParentOfFragmentRemoving(fragment: Fragment): Boolean {
        var isAnyParentRemoving = false

        var parent = fragment.parentFragment
        while (!isAnyParentRemoving && parent != null) {
            isAnyParentRemoving = parent.isRemoving
            parent = parent.parentFragment
        }
        return isAnyParentRemoving
    }

    companion object {
        val CONTAINER_UUID = "${FragmentLifecycleHelper::class.java.canonicalName}.CONTAINER_UUID"
    }
}