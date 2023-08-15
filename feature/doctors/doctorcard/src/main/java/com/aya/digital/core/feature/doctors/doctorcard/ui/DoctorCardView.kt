package com.aya.digital.core.feature.doctors.doctorcard.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.*
import com.aya.digital.core.feature.doctors.doctorcard.DoctorCardMode
import com.aya.digital.core.feature.doctors.doctorcard.databinding.ViewDoctorCardBinding
import com.aya.digital.core.feature.doctors.doctorcard.di.doctorCardDiModule
import com.aya.digital.core.feature.doctors.doctorcard.ui.model.DoctorCardStateTransformer
import com.aya.digital.core.feature.doctors.doctorcard.ui.model.DoctorCardUiModel
import com.aya.digital.core.feature.doctors.doctorcard.viewmodel.DoctorCardSideEffects
import com.aya.digital.core.feature.doctors.doctorcard.viewmodel.DoctorCardState
import com.aya.digital.core.feature.doctors.doctorcard.viewmodel.DoctorCardViewModel
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
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
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toLocalDateTime
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on


class DoctorCardView :
    DiFragment<ViewDoctorCardBinding, DoctorCardViewModel, DoctorCardState, DoctorCardSideEffects, DoctorCardUiModel, DoctorCardStateTransformer>() {

    private var param: Param by argument("param")

    private val viewModelFactory: ((Unit) -> DoctorCardViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> DoctorCardStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate {
                DoctorDetailsTitleDelegate()
            }
            delegate {
                DoctorDetailsBioDelegate(viewModel::onBioReadMoreClicked)
            }
            delegate { DoctorSlotDelegate(viewModel::onSlotClicked) }
            delegate { DoctorDateTitleDelegate() }
            delegate { DoctorDetailsInsuranceDelegate() }
            delegate { DoctorDetailsLocationDelegate() }
        }
    }

    private lateinit var lm: GridLayoutManager

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
        binding.toolbar.backButton bindClick { viewModel.onBack() }
        binding.bookBtn bindClick { viewModel.onBookClicked() }
        binding.detailsBtn bindClick { viewModel.onDetailsClicked() }
        binding.btnSelectDate bindClick { viewModel.onChooseDateClicked() }
        binding.toolbar.favoriteButton bindClick { viewModel.onFavoriteClicked() }
        with(binding.recycler) {
            itemAnimator = null
            setHasFixedSize(true)
            setItemViewCacheSize(30)
            isNestedScrollingEnabled = false


            lm = GridLayoutManager(
                context,
                4,
                RecyclerView.VERTICAL,
                false
            )


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

    override fun sideEffect(sideEffect: DoctorCardSideEffects) =
        when (sideEffect) {
            is DoctorCardSideEffects.Error -> processErrorSideEffect(sideEffect.error)
            is DoctorCardSideEffects.ShowCustomDateDialog -> showDatePicker(sideEffect.selectableDates)
        }

    override fun render(state: DoctorCardState) {
        stateTransformer(state).run {
            doctorName?.let {
                binding.toolbar.nameTv.text = it
            }
            doctorSpeciality?.let {
                binding.toolbar.title.text = it
            }
            doctorClinic?.let {
                binding.toolbar.clinicTv.text = it
            }
            doctorAvatar?.let {
                Glide
                    .with(binding.toolbar.avatar)
                    .load(it)
                    .transform(
                        CircleCrop()
                    )
                    .dontAnimate()
                    .into(binding.toolbar.avatar)
            }
            data?.let {
                adapter.items = it
                if (binding.recycler.adapter == null) {
                    binding.recycler.swapAdapter(adapter, true)
                    lm.spanSizeLookup = DoctorCardSpanSizeLookup(adapter)
                }
            }
            doctorCardMode.let { doctorCardMode ->
                when (doctorCardMode) {
                    DoctorCardMode.ShowingDetailsInfo -> {
                        binding.bookBtn.unselect()
                        binding.detailsBtn.select()
                    }

                    DoctorCardMode.ShowingSlots -> {
                        binding.bookBtn.select()
                        binding.detailsBtn.unselect()
                    }
                }
            }
            isFavorite.let {
                isFavorite
                val favoriteIcn =
                    if (isFavorite) com.aya.digital.core.baseresources.R.drawable.ic_favorite_white_active else com.aya.digital.core.baseresources.R.drawable.ic_favorite_white
                Glide
                    .with(binding.toolbar.favoriteButton)
                    .load(favoriteIcn)
                    .dontAnimate()
                    .into(binding.toolbar.favoriteButton)
            }
        }
    }

    private fun showDatePicker(selectableDates: List<LocalDate>) {
        val constraintsBuilderSelectable = CalendarConstraints.Builder()
            .setValidator(DateValidatorSelectableDays(selectableDates.map { it.toJavaLocalDate() }))
        val materialDatePicker = MaterialDatePicker.Builder.datePicker()
            .setCalendarConstraints(constraintsBuilderSelectable.build())
            .build()
        materialDatePicker
            .addOnPositiveButtonClickListener {
                val date = Instant.fromEpochMilliseconds(it)
                    .toLocalDateTime(TimeZone.currentSystemDefault()).date
                viewModel.onDateSelected(date)
            }
        materialDatePicker
            .show(childFragmentManager, "BirthDAY")
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
