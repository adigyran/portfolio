package com.aya.digital.core.feature.tabviews.appointmentsscheduler.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.feature.tabviews.appointmentsscheduler.databinding.ViewAppointmentsSchedulerBinding
import com.aya.digital.core.feature.tabviews.appointmentsscheduler.di.appointmentsSchedulerDiModule
import com.aya.digital.core.feature.tabviews.appointmentsscheduler.ui.model.AppointmentsSchedulerStateTransformer
import com.aya.digital.core.feature.tabviews.appointmentsscheduler.ui.model.AppointmentsSchedulerUiModel
import com.aya.digital.core.feature.tabviews.appointmentsscheduler.viewmodel.AppointmentsSchedulerSideEffects
import com.aya.digital.core.feature.tabviews.appointmentsscheduler.viewmodel.AppointmentsSchedulerState
import com.aya.digital.core.feature.tabviews.appointmentsscheduler.viewmodel.AppointmentsSchedulerViewModel
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerday.ui.AppointmentsSchedulerDayDelegate
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.ui.AppointmentsSchedulerAppointmentSlotDelegate
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.ui.AppointmentsSchedulerEmptyDayDelegate
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.ui.AppointmentsSchedulerEmptySlotDelegate
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.ui.AppointmentsSchedulerMultiAppointmentSlotDelegate
import com.google.android.material.datepicker.MaterialDatePicker
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId


class AppointmentsSchedulerView :
    DiFragment<ViewAppointmentsSchedulerBinding, AppointmentsSchedulerViewModel, AppointmentsSchedulerState, AppointmentsSchedulerSideEffects, AppointmentsSchedulerUiModel, AppointmentsSchedulerStateTransformer>() {

    private val viewModelFactory: ((Unit) -> AppointmentsSchedulerViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> AppointmentsSchedulerStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val daysPositions = mutableMapOf<LocalDate, Int>()

    private val daysAdapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate {
                AppointmentsSchedulerDayDelegate(viewModel::onDayClicked) { day, position ->
                    daysPositions[day] = position
                }
            }
        }
    }

    private val slotsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate {
                AppointmentsSchedulerEmptyDayDelegate { viewModel.onCreateScheduleClicked() }
            }
            delegate {
                AppointmentsSchedulerAppointmentSlotDelegate(viewModel::onSlotClicked)
            }
            delegate {
                AppointmentsSchedulerMultiAppointmentSlotDelegate{ viewModel.onSlotClicked(it) }
            }
            delegate {
                AppointmentsSchedulerEmptySlotDelegate({})
            }
        }
    }

    private val snapHelper = LinearSnapHelper()
    private lateinit var smoothScroller: RecyclerView.SmoothScroller
    private lateinit var daysLm: LinearLayoutManager
    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = false
            viewModel.onRefresh()
        }
        binding.toolbar.calendarButton bindClick { showDatePicker() }
        recyclers.add(binding.toolbar.recyclerDays)
        recyclers.add(binding.recyclerSlots)
        smoothScroller = object : LinearSmoothScroller(requireContext()) {
            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }
        with(binding.toolbar.recyclerDays) {
            itemAnimator = null
            setHasFixedSize(true)
            setItemViewCacheSize(30)
            isNestedScrollingEnabled = false

            daysLm = LinearLayoutManager(
                context,
                RecyclerView.HORIZONTAL,
                false
            )
            layoutManager = daysLm
            snapHelper.attachToRecyclerView(this)
            addItemDecoration(AppointmentsSchedulerDaysTabDecoration(context))
        }
        with(binding.recyclerSlots) {
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
            addItemDecoration(AppointmentsSchedulerSlotsTabDecoration(context))
        }


    }


    private fun showDatePicker() {
        val materialDatePicker = MaterialDatePicker.Builder.datePicker()
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
            .show(childFragmentManager, "ScheduleDAY")
    }


    override fun provideDiModule(): DI.Module =
        appointmentsSchedulerDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewAppointmentsSchedulerBinding =
        ViewAppointmentsSchedulerBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: AppointmentsSchedulerSideEffects) =
        when (sideEffect) {
            is AppointmentsSchedulerSideEffects.Error -> processErrorSideEffect(sideEffect.error)
        }

    private fun scrollToDayPosition(day: LocalDate) {
        val dayPosition = day.dayOfMonth - 1
        binding.toolbar.recyclerDays.smoothScrollToPosition(dayPosition)
    }

    private var lastSelectedDay: LocalDate? = null
    override fun render(state: AppointmentsSchedulerState) {
        stateTransformer(state).run {
            selectedDayDate?.let { lastSelectedDay = it }
            daysData?.let {
                daysAdapter.items = it
                if (binding.toolbar.recyclerDays.adapter == null) {
                    binding.toolbar.recyclerDays.swapAdapter(daysAdapter, true)
                    daysAdapter.registerAdapterDataObserver(object :
                        RecyclerView.AdapterDataObserver() {

                        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                            lastSelectedDay?.let { localDate ->
                                scrollToDayPosition(localDate)
                            }
                        }

                        override fun onItemRangeChanged(
                            positionStart: Int,
                            itemCount: Int,
                            payload: Any?
                        ) {
                            lastSelectedDay?.let { localDate ->
                                scrollToDayPosition(localDate)
                            }
                        }
                    })
                }


            }
            slotsData?.let {
                slotsAdapter.items = it
                if (binding.recyclerSlots.adapter == null) {
                    binding.recyclerSlots.swapAdapter(slotsAdapter, true)
                }
            }
            monthName?.let {
                binding.toolbar.dateTv.text = it
            }

        }
    }

    override fun provideViewModel(): AppointmentsSchedulerViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): AppointmentsSchedulerStateTransformer =
        stateTransformerFactory(Unit)

}
