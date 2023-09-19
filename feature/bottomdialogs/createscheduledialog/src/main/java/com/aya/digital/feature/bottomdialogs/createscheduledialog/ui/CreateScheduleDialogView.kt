package com.aya.digital.feature.bottomdialogs.createscheduledialog.ui

import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.argument
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.createFragment
import com.aya.digital.core.ext.toggleAvailability
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiBottomSheetDialogFragment
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.createschedule.telemed.ui.SchedulerCreateScheduleIsTelemedDelegate
import com.aya.digital.core.ui.delegates.components.fields.dropdown.ui.DropDownFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.dropdown.ui.DropDownFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.selection.ui.SelectionFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.selection.ui.SelectionFieldDelegateListeners
import com.aya.digital.feature.bottomdialogs.createscheduledialog.FieldsTags
import com.aya.digital.feature.bottomdialogs.createscheduledialog.databinding.ViewCreateScheduleDialogBinding
import com.aya.digital.feature.bottomdialogs.createscheduledialog.di.createScheduleDialogDiModule
import com.aya.digital.feature.bottomdialogs.createscheduledialog.ui.model.CreateScheduleDialogStateTransformer
import com.aya.digital.feature.bottomdialogs.createscheduledialog.ui.model.CreateScheduleDialogUiModel
import com.aya.digital.feature.bottomdialogs.createscheduledialog.viewmodel.CreateScheduleDialogSideEffects
import com.aya.digital.feature.bottomdialogs.createscheduledialog.viewmodel.CreateScheduleDialogState
import com.aya.digital.feature.bottomdialogs.createscheduledialog.viewmodel.CreateScheduleDialogViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.timepicker.MaterialTimePicker
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on
import timber.log.Timber
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class CreateScheduleDialogView :
    DiBottomSheetDialogFragment<ViewCreateScheduleDialogBinding, CreateScheduleDialogViewModel, CreateScheduleDialogState, CreateScheduleDialogSideEffects, CreateScheduleDialogUiModel, CreateScheduleDialogStateTransformer>() {

    private var param: Param by argument("param")

    private var initialised: Boolean = false

    private val viewModelFactory: ((Unit) -> CreateScheduleDialogViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> CreateScheduleDialogStateTransformer) by kodein.on(
        context = this
    ).factory()

    override fun provideViewModel(): CreateScheduleDialogViewModel = viewModelFactory(Unit)


    override fun provideStateTransformer(): CreateScheduleDialogStateTransformer =
        stateTransformerFactory(Unit)


    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { SchedulerCreateScheduleIsTelemedDelegate(viewModel::onTelemedChecked) }
            delegate { SelectionFieldDelegate(SelectionFieldDelegateListeners(viewModel::onSelectFieldClicked)) }
            delegate {
                DropDownFieldDelegate(DropDownFieldDelegateListeners { tag, selectedItem ->
                    viewModel.dropDownSelected(
                        tag,
                        selectedItem.tag
                    )
                })
            }
        }
    }

    private lateinit var lm: LinearLayoutManager

    override fun provideDiModule(): DI.Module =
        createScheduleDialogDiModule(tryTyGetParentRouter(), param)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
        Timber.d("AAAA")
        binding.btnClose bindClick { viewModel.close() }
        binding.btnBack.bindClick { viewModel.close() }
        binding.btnCreate bindClick { viewModel.onCreateScheduleClicked() }
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetBehavior.skipCollapsed = true
        with(binding.recycler) {
            itemAnimator = null
            lm = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )


            layoutManager = lm
            addItemDecoration(CreateScheduleDecoration())
        }
    }

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewCreateScheduleDialogBinding =
        ViewCreateScheduleDialogBinding.inflate(inflater, container, false)

    override fun render(state: CreateScheduleDialogState) {
        stateTransformer(state).run {

            data?.let {
                adapter.items = it
                if (binding.recycler.adapter == null) {
                    binding.recycler.swapAdapter(adapter, true)
                }
            }
            createEnabled.run { binding.btnCreate.toggleAvailability(createEnabled) }

        }
    }

    private fun showTimePicker(tag: Int, date: LocalDate, time: LocalTime?) {
        val materialTimePicker = MaterialTimePicker.Builder()
            .build()

        materialTimePicker
            .addOnPositiveButtonClickListener {
                val hour = materialTimePicker.hour
                val minute = materialTimePicker.minute
                val selectedTime = date.atTime(hour, minute)
                viewModel.onTimePicked(tag, selectedTime)
            }
        materialTimePicker.show(childFragmentManager, "SELECT_TIME")


        /*val materialDatePicker = MaterialDatePicker.Builder.datePicker()
            //.setDayViewDecorator(CircleIndicatorDayDecorator())
            // .setCalendarConstraints(constraintsBuilderSelectable.build())
            .build()
        materialDatePicker
            .addOnPositiveButtonClickListener {
                val selectedDate =
                    Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
                viewModel.onSelectCalendarDate(selectedDate)
            }
        materialDatePicker
            .show(childFragmentManager, "ScheduleDAY")*/
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }

    override fun sideEffect(sideEffect: CreateScheduleDialogSideEffects) =
        when (sideEffect) {
            is CreateScheduleDialogSideEffects.Error -> processErrorSideEffect(sideEffect.error)
            is CreateScheduleDialogSideEffects.ShowStartTimePicker -> showTimePicker(
                tag = FieldsTags.START_TIME_FIELD,
                date = sideEffect.date,
                time = sideEffect.startTime
            )

            is CreateScheduleDialogSideEffects.ShowEndTimePicker -> showTimePicker(
                tag = FieldsTags.END_TIME_FIELD,
                date = sideEffect.date,
                time = sideEffect.endTime
            )

            is CreateScheduleDialogSideEffects.ShowTimePicker -> showTimePicker(
                tag = sideEffect.tag,
                date = sideEffect.date,
                time = sideEffect.selectedTime
            )
        }


    @Parcelize
    class Param(
        val requestCode: String,
        val date: LocalDate?
    ) : Parcelable

    companion object {
        fun getNewInstance(
            requestCode: String,
            date: LocalDate?
        ): CreateScheduleDialogView =
            createFragment(
                Param(
                    requestCode = requestCode,
                    date = date
                )
            )
    }
}
