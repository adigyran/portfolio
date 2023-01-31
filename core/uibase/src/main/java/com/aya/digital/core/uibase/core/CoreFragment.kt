package com.aya.digital.core.uibase.core

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.aya.digital.core.ext.ContextAware
import com.aya.digital.core.ext.colors
import com.aya.digital.core.ext.setStatusBarColor
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.utils.ChildKodeinProvider
import com.aya.digital.core.navigation.utils.ParentRouterProvider
import com.aya.digital.core.util.retainedinstancemanager.IHasRetainedInstance
import com.aya.digital.core.util.retainedinstancemanager.IdProvider
import org.kodein.di.DI
import java.util.*

abstract class CoreFragment<Binding : ViewBinding> : Fragment(), ContextAware,
    IHasRetainedInstance<DI>, IdProvider {
    protected open val statusBarId: Int? = null
    protected var originalInputMode: Int? = null
    protected open val inputMode: Int? = null

    private var originalIsLightStatusBar: Boolean? = null
    protected open val isLightStatusBar: Boolean? = null

    protected val recyclers: MutableList<RecyclerView> =
        mutableListOf()//Список ресайклеров. Нужен, чтоб занулялись адаптеры
    protected val pagers: MutableList<ViewPager2> =
        mutableListOf()//Список пейджеров. Нужен, чтоб занулялись адаптеры

    protected var _binding: Binding? = null
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        val args = arguments

        when {
            args == null -> {
                val bundle = Bundle()
                bundle.putString(
                    CONTAINER_UUID, UUID.randomUUID().toString()
                )
                arguments = bundle
            }
            args.getString(CONTAINER_UUID) == null -> args.putString(
                CONTAINER_UUID, UUID.randomUUID().toString()
            )
        }

        initDi()

        super.onCreate(savedInstanceState)
    }

    override fun onDestroyView() {
        recyclers.forEach { it.adapter = null }
        recyclers.clear()

        pagers.forEach { it.adapter = null }
        pagers.clear()

        if (originalInputMode != null && inputMode != null) activity?.window?.setSoftInputMode(
            originalInputMode!!
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (originalIsLightStatusBar != null && isLightStatusBar != null) toggleStatusBar(
                originalIsLightStatusBar!!)
        }

        super.onDestroyView()

        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareCreatedUi(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        originalInputMode = activity?.window?.attributes?.softInputMode
        inputMode?.let { activity?.window?.setSoftInputMode(it) }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            originalIsLightStatusBar = isLightStatusBar()
            isLightStatusBar?.let {
                toggleStatusBar(it)
            }
        }

        _binding = provideViewBinding(inflater, container)
        val view = binding.root

        prepareUi(savedInstanceState)

        return view
    }



    override fun onResume() {
        statusBarId?.let { setStatusBarColor(colors[it]) }
        super.onResume()
    }

    final override fun getUuid(): String = requireArguments().getString(
        CONTAINER_UUID
    )!!

    open fun initDi() = Unit

    open fun prepareUi(savedInstanceState: Bundle?) = Unit

    open fun prepareCreatedUi(savedInstanceState: Bundle?) = Unit

    abstract fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): Binding

    override fun getContext(): Context = super.requireContext()

    protected fun getClosestParentKodein(): DI = when {
        parentFragment is ChildKodeinProvider -> (requireParentFragment() as ChildKodeinProvider).getChildKodein()
        activity is ChildKodeinProvider -> (requireActivity() as ChildKodeinProvider).getChildKodein()
        else -> com.aya.digital.base.appbase.BaseApp.appInstance.di
    }

    protected fun tryTyGetParentRouter(): CoordinatorRouter = when {
        parentFragment is ParentRouterProvider -> (parentFragment as ParentRouterProvider).getParentRouter()
        activity is ParentRouterProvider -> (activity as ParentRouterProvider).getParentRouter()
        else -> throw RuntimeException("Wtf")
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun toggleStatusBar(isLight: Boolean) {
        var systemUiVisibility: Int = requireActivity().window.decorView.systemUiVisibility
        systemUiVisibility = if (isLight) {
            systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
        requireActivity().window.decorView.systemUiVisibility = systemUiVisibility
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isLightStatusBar(): Boolean {
        return (requireActivity().window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR) == View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    companion object {
        private val CONTAINER_UUID = "${CoreFragment::class.java.canonicalName}.CONTAINER_UUID"
    }

    protected fun RecyclerView.clearOnDestroyView() {
        recyclers.add(this)
    }

    protected fun ViewPager2.clearOnDestroyView() {
        pagers.add(this)
    }
}