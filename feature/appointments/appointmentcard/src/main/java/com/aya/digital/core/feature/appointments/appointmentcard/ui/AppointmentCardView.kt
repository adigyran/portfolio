package com.aya.digital.core.feature.appointments.appointmentcard.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.localisation.R as LocR
import com.aya.digital.core.ext.*
import com.aya.digital.core.feature.appointments.appointmentcard.databinding.ViewAppointmentCardBinding
import com.aya.digital.core.feature.appointments.appointmentcard.di.appointmentCardDiModule
import com.aya.digital.core.feature.appointments.appointmentcard.ui.model.AppointmentCardStateTransformer
import com.aya.digital.core.feature.appointments.appointmentcard.ui.model.AppointmentCardUiModel
import com.aya.digital.core.feature.appointments.appointmentcard.viewmodel.AppointmentCardSideEffects
import com.aya.digital.core.feature.appointments.appointmentcard.viewmodel.AppointmentCardState
import com.aya.digital.core.feature.appointments.appointmentcard.viewmodel.AppointmentCardViewModel
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.base.utils.DateValidatorSelectableDays
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.AppointmentTelemedDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.DoctorDetailsBioDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.DoctorDetailsInsuranceDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.DoctorDetailsLocationDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.DoctorDetailsTitleDelegate
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on


internal class AppointmentCardView :
    DiFragment<ViewAppointmentCardBinding, AppointmentCardViewModel, AppointmentCardState, AppointmentCardSideEffects, AppointmentCardUiModel, AppointmentCardStateTransformer>() {

    private var param: Param by argument("param")

    private val viewModelFactory: ((Unit) -> AppointmentCardViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> AppointmentCardStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate {
                DoctorDetailsTitleDelegate()
            }
            delegate {
                DoctorDetailsBioDelegate(viewModel::onCommentReadMoreClicked)
            }
            delegate { DoctorDetailsInsuranceDelegate() }
            delegate { DoctorDetailsLocationDelegate() }
            delegate { AppointmentTelemedDelegate(viewModel::onTelemedClicked) }
        }
    }


    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
        binding.toolbar.backButton bindClick {viewModel.onBack()}
        binding.btnCancelAppointment bindClick {showCancelAppointmentDialog()}
        binding.btnRescheduleAppointment bindClick {viewModel.onRescheduleAppointment()}
        with(binding.recycler) {
            itemAnimator = null
            setHasFixedSize(true)
            setItemViewCacheSize(30)
            isNestedScrollingEnabled = false
            val lm = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )

            layoutManager = lm

            addItemDecoration(AppointmentCardDecoration())
        }
    }

    override fun provideDiModule(): DI.Module = appointmentCardDiModule(tryTyGetParentRouter(), param)

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewAppointmentCardBinding =
        ViewAppointmentCardBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: AppointmentCardSideEffects) =
        when (sideEffect) {
            is AppointmentCardSideEffects.Error -> processErrorSideEffect(sideEffect.error)
            is AppointmentCardSideEffects.ShowCustomDateDialog -> showDatePicker(sideEffect.selectableDates)
            AppointmentCardSideEffects.ShowTelemedicineNotReadyDialog -> showTelemedicineNotReadyDialog()
        }

    private fun showTelemedicineNotReadyDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Error")
            .setMessage("Telemedicine call available only before 15 mins of appointment time. Please wait")
            .setPositiveButton("OK") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    override fun render(state: AppointmentCardState) {
        stateTransformer(state).run {
            appointmentDateTimeText?.let {
                binding.toolbar.title.text = it
            }
            data?.let {
                adapter.items = it
                if (binding.recycler.adapter == null) {
                    binding.recycler.swapAdapter(adapter, true)
                }
            }
            participantLines?.let {pair->
                binding.toolbar.nameTv.text = pair.first
                binding.toolbar.clinicTv.text = pair.second
            }
            setParticipantAvatar(participantAvatarLink)

        }
    }

    private fun setParticipantAvatar(avatar: String?) {
        avatar?.let {
            Glide
                .with(binding.toolbar.avatar)
                .load(it)
                .transform(
                    CircleCrop()
                )
                .dontAnimate()
                .into(binding.toolbar.avatar)
        }
    }

    private fun showDatePicker(selectableDates: List<LocalDate>) {
        val constraintsBuilderSelectable = CalendarConstraints.Builder()
            .setValidator(DateValidatorSelectableDays(selectableDates))
        val materialDatePicker = MaterialDatePicker.Builder.datePicker()
            .setCalendarConstraints(constraintsBuilderSelectable.build())
            .build()
        materialDatePicker
            .addOnPositiveButtonClickListener {
                val date = Instant.fromEpochMilliseconds(it)
                    .toLocalDateTime(TimeZone.currentSystemDefault()).date
              //  viewModel.onDateSelected(date)
            }
        materialDatePicker
            .show(childFragmentManager, "BirthDAY")
    }

    private fun showCancelAppointmentDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(strings[LocR.string.cancel_dialog_title])
            .setMessage(strings[LocR.string.appointment_card_cancel_appointment_dialog_message])
            .setNegativeButton(strings[LocR.string.cancel_dialog_negative]) { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton(strings[LocR.string.cancel_dialog_positive]) { dialog, which ->
                viewModel.onCancelAppointment()
                dialog.dismiss()
            }
            .show()
    }

    @Parcelize
    class Param(
        val appointmentId: Int
    ) : Parcelable

    override fun provideViewModel(): AppointmentCardViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): AppointmentCardStateTransformer =
        stateTransformerFactory(Unit)

    companion object {
        fun getNewInstance(appointmentId: Int): AppointmentCardView =
            createFragment(
                Param(appointmentId)
            )
    }
}
