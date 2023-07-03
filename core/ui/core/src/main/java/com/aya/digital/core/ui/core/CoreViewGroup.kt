package com.aya.digital.core.ui.core

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.aya.digital.base.appbase.BaseApp
import com.aya.digital.core.ext.ContextAware
import com.aya.digital.core.navigation.utils.ChildKodeinProvider
import com.aya.digital.core.util.retainedinstancemanager.IHasRetainedInstance
import com.aya.digital.core.util.retainedinstancemanager.IdProvider
import org.kodein.di.DI

abstract class CoreViewGroup<Binding : ViewBinding> @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr), ContextAware{
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



    init {
        initDi()
        _binding = this.provideViewBinding(LayoutInflater.from(context))
        val view = binding.root
        this.prepareUi(null)
        this.prepareCreatedUi(null)
    }

    open fun initDi() = Unit

    open fun prepareUi(savedInstanceState: Bundle?) = Unit

    open fun prepareCreatedUi(savedInstanceState: Bundle?) = Unit

    abstract fun provideViewBinding(inflater: LayoutInflater): Binding

    override fun getContextAware(): Context = context

    protected fun getClosestParentKodein(): DI = when {
        context is ChildKodeinProvider -> (context as ChildKodeinProvider).getChildKodein()
        else -> BaseApp.appInstance.di
    }

    protected fun getAppInstance() = BaseApp.appInstance


    companion object {
        private val CONTAINER_UUID = "${CoreViewGroup::class.java.canonicalName}.CONTAINER_UUID"
    }

    protected fun RecyclerView.clearOnDestroyView() {
        recyclers.add(this)
    }

    protected fun ViewPager2.clearOnDestroyView() {
        pagers.add(this)
    }
}