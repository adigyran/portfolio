package com.aya.digital.core.feature.doctors.doctorcard.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.argument
import com.aya.digital.core.ext.createFragment
import com.aya.digital.core.feature.doctors.doctorcard.databinding.ViewDoctorCardBinding
import com.aya.digital.core.feature.doctors.doctorcard.di.doctorCardDiModule
import com.aya.digital.core.feature.doctors.doctorcard.ui.model.DoctorCardStateTransformer
import com.aya.digital.core.feature.doctors.doctorcard.ui.model.DoctorCardUiModel
import com.aya.digital.core.feature.doctors.doctorcard.viewmodel.DoctorCardState
import com.aya.digital.core.feature.doctors.doctorcard.viewmodel.DoctorCardViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class DoctorCardView :
    DiFragment<ViewDoctorCardBinding, DoctorCardViewModel, DoctorCardState, BaseSideEffect, DoctorCardUiModel, DoctorCardStateTransformer>() {

    private var param: Param by argument("param")

    private val viewModelFactory: ((Unit) -> DoctorCardViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> DoctorCardStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {

        }
    }

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
        with(binding.recycler) {
            itemAnimator = null
            setHasFixedSize(true)
            setItemViewCacheSize(30)
            isNestedScrollingEnabled = false

            val lm = GridLayoutManager(
                context,
                4,
                RecyclerView.VERTICAL,
                false
            )
            lm.spanSizeLookup = DoctorCardSpanSizeLookup(binding.recycler)
            layoutManager = lm
            addItemDecoration(DoctorCardDecoration())
        }
    }

    override fun provideDiModule(): DI.Module = doctorCardDiModule(tryTyGetParentRouter(), param)

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDoctorCardBinding =
        ViewDoctorCardBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: DoctorCardState) {
        stateTransformer(state).run {

        }
    }

    @Parcelize
    class Param(
        val doctorId: Int
    ) : Parcelable

    override fun provideViewModel(): DoctorCardViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): DoctorCardStateTransformer =
        stateTransformerFactory(Unit)

    companion object {
        fun getNewInstance(doctorId: Int): DoctorCardView =
            createFragment(
                Param(doctorId)
            )
    }
}
