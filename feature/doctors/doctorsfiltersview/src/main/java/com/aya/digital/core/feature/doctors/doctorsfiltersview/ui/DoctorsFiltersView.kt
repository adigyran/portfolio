package com.aya.digital.core.feature.doctors.doctorsfiltersview.ui

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import com.aya.digital.core.feature.doctors.doctorsfiltersview.databinding.ViewDoctorsFiltersViewBinding
import com.aya.digital.core.feature.doctors.doctorsfiltersview.di.doctorsFiltersViewDiModule
import com.aya.digital.core.feature.doctors.doctorsfiltersview.ui.model.DoctorsFiltersViewStateTransformer
import com.aya.digital.core.feature.doctors.doctorsfiltersview.ui.model.DoctorsFiltersViewUiModel
import com.aya.digital.core.feature.doctors.doctorsfiltersview.viewmodel.DoctorsFiltersViewSideEffects
import com.aya.digital.core.feature.doctors.doctorsfiltersview.viewmodel.DoctorsFiltersViewState
import com.aya.digital.core.feature.doctors.doctorsfiltersview.viewmodel.DoctorsFiltersViewViewModel
import com.aya.digital.core.ui.base.screens.DiViewGroup
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on


class DoctorsFiltersView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    DiViewGroup<ViewDoctorsFiltersViewBinding, DoctorsFiltersViewViewModel, DoctorsFiltersViewState, DoctorsFiltersViewSideEffects, DoctorsFiltersViewUiModel, DoctorsFiltersViewStateTransformer>(
        context,
        attrs,
        defStyleAttr
    ) {


    private val viewModelFactory: ((Unit) -> DoctorsFiltersViewViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> DoctorsFiltersViewStateTransformer) by kodein.on(
        context = this
    ).factory()


    private lateinit var lm: GridLayoutManager

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
    }

    override fun provideDiModule(): DI.Module = doctorsFiltersViewDiModule()

    override fun provideViewBinding(
        inflater: LayoutInflater,
    ): ViewDoctorsFiltersViewBinding =
        ViewDoctorsFiltersViewBinding.inflate(inflater, this, true)

    override fun sideEffect(sideEffect: DoctorsFiltersViewSideEffects) =
        when (sideEffect) {
            is DoctorsFiltersViewSideEffects.Error -> processErrorSideEffect(sideEffect.error)
        }

    override fun render(state: DoctorsFiltersViewState) {
        stateTransformer(state).run {

        }
    }


    @Parcelize
    class Param(
        val doctorId: Int
    ) : Parcelable

    override fun provideViewModel(): DoctorsFiltersViewViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): DoctorsFiltersViewStateTransformer =
        stateTransformerFactory(Unit)

}
