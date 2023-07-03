package com.aya.digital.core.feature.doctors.doctorsfiltersview.ui

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.*
import com.aya.digital.core.feature.doctors.doctorsfiltersview.databinding.ViewDoctorsFiltersViewBinding
import com.aya.digital.core.feature.doctors.doctorsfiltersview.di.doctorsFiltersViewDiModule
import com.aya.digital.core.feature.doctors.doctorsfiltersview.ui.model.DoctorsFiltersViewStateTransformer
import com.aya.digital.core.feature.doctors.doctorsfiltersview.ui.model.DoctorsFiltersViewUiModel
import com.aya.digital.core.feature.doctors.doctorsfiltersview.viewmodel.DoctorCardSideEffects
import com.aya.digital.core.feature.doctors.doctorsfiltersview.viewmodel.DoctorCardState
import com.aya.digital.core.feature.doctors.doctorsfiltersview.viewmodel.DoctorCardViewModel
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiViewGroup
import com.aya.digital.core.ui.base.utils.DateValidatorSelectableDays
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.DoctorDetailsBioDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.DoctorDetailsInsuranceDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.DoctorDetailsLocationDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.DoctorDetailsTitleDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.ui.DoctorDateTitleDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.ui.DoctorSlotDelegate
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on


class DoctorsFiltersView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    DiViewGroup<ViewDoctorsFiltersViewBinding, DoctorCardViewModel, DoctorCardState, DoctorCardSideEffects, DoctorsFiltersViewUiModel, DoctorsFiltersViewStateTransformer>(
        context,
        attrs,
        defStyleAttr
    ) {


    private val viewModelFactory: ((Unit) -> DoctorCardViewModel) by kodein.on(
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

    override fun getUuid(): String {
        TODO("Not yet implemented")
    }

    override fun provideViewBinding(
        inflater: LayoutInflater,
    ): ViewDoctorsFiltersViewBinding =
        ViewDoctorsFiltersViewBinding.inflate(inflater, this, true)

    override fun sideEffect(sideEffect: DoctorCardSideEffects) =
        when (sideEffect) {
            is DoctorCardSideEffects.Error -> processErrorSideEffect(sideEffect.error)
        }

    override fun render(state: DoctorCardState) {
        stateTransformer(state).run {

        }
    }


    @Parcelize
    class Param(
        val doctorId: Int
    ) : Parcelable

    override fun provideViewModel(): DoctorCardViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): DoctorsFiltersViewStateTransformer =
        stateTransformerFactory(Unit)

}
