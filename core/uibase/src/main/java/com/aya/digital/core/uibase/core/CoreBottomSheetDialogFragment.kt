package com.aya.digital.core.uibase.core

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.aya.digital.core.ext.ContextAware
import com.aya.digital.core.ext.colors
import com.aya.digital.core.ext.setStatusBarColor
import com.aya.digital.core.navigation.ChildKodeinProvider
import com.aya.digital.core.navigation.ParentRouterProvider
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.BaseApp
import com.aya.digital.core.util.retainedinstancemanager.IHasRetainedInstance
import com.aya.digital.core.util.retainedinstancemanager.IdProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.kodein.di.DI
import java.util.*

abstract class CoreBottomSheetDialogFragment<Binding : ViewBinding> : BottomSheetDialogFragment(),
    ContextAware,
    IHasRetainedInstance<DI>, IdProvider {
    protected open val statusBarId: Int? = null
    protected var originalInputMode: Int? = null
    protected open val inputMode: Int? = null

    protected open val skipCollapsed: Boolean? = null
    protected open val bottomSheetInitState: Int? = null
    protected open val isDialogHideable: Boolean? = null
    protected open lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>

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

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        bottomSheetBehavior = (dialog as BottomSheetDialog).behavior
        dialog.setOnShowListener {
            skipCollapsed?.let { bottomSheetBehavior.skipCollapsed = it }
            bottomSheetInitState?.let { bottomSheetBehavior.state = it }
            isDialogHideable?.let { bottomSheetBehavior.isHideable = it }
        }

        //Перехват onBackPressed
        dialog.setOnKeyListener { _, keyCode, event ->
            if (isDialogHideable == false) {
                return@setOnKeyListener true
            }

            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                return@setOnKeyListener onBackPressed()
            }
            return@setOnKeyListener false
        }

        isDialogHideable?.let {
            isCancelable = it
            dialog.setCanceledOnTouchOutside(it)
        }

        return dialog
    }

    override fun onDestroyView() {
        recyclers.forEach { it.adapter = null }
        recyclers.clear()

        pagers.forEach { it.adapter = null }
        pagers.clear()

        if (originalInputMode != null && inputMode != null) activity?.window?.setSoftInputMode(
            originalInputMode!!
        )

        super.onDestroyView()

        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        originalInputMode = activity?.window?.attributes?.softInputMode
        inputMode?.let { activity?.window?.setSoftInputMode(it) }

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

    open fun onBackPressed(): Boolean = false

    abstract fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): Binding

    override fun getContext(): Context = super.requireContext()

    protected fun getClosestParentKodein(): DI = when {
        parentFragment is ChildKodeinProvider -> (requireParentFragment() as ChildKodeinProvider).getChildKodein()
        activity is ChildKodeinProvider -> (requireActivity() as ChildKodeinProvider).getChildKodein()
        else -> BaseApp.appInstance.di
    }

    protected fun tryTyGetParentRouter(): CoordinatorRouter = when {
        parentFragment is ParentRouterProvider -> (parentFragment as ParentRouterProvider).getParentRouter()
        activity is ParentRouterProvider -> (activity as ParentRouterProvider).getParentRouter()
        else -> throw RuntimeException("Wtf")
    }

    companion object {
        private val CONTAINER_UUID =
            "${CoreBottomSheetDialogFragment::class.java.canonicalName}.CONTAINER_UUID"
    }

    protected fun RecyclerView.clearOnDestroyView() {
        recyclers.add(this)
    }

    protected fun ViewPager2.clearOnDestroyView() {
        pagers.add(this)
    }
}