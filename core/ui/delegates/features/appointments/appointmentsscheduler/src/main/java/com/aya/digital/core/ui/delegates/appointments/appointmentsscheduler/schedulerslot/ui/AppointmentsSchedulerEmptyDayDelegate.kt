package com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.ui

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ImageSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.colors
import com.aya.digital.core.ext.drawables
import com.aya.digital.core.ext.strings
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.base.ext.appendExtendedImage
import com.aya.digital.core.ui.base.ext.createTouchableSpan
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.model.AppointmentsSchedulerEmptyDayUIModel
import com.aya.digital.core.localisation.R as LocR
import com.aya.digital.core.ui.delegates.features.appointments.appointmentsscheduler.databinding.ItemAppointmentsSchedulerEmptyDayBinding

class AppointmentsSchedulerEmptyDayDelegate(private val createScheduleClick: () -> Unit) :
    BaseDelegate<AppointmentsSchedulerEmptyDayUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is AppointmentsSchedulerEmptyDayUIModel


    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<AppointmentsSchedulerEmptyDayUIModel> {
        val binding =
            ItemAppointmentsSchedulerEmptyDayBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemAppointmentsSchedulerEmptyDayBinding) :
        BaseViewHolder<AppointmentsSchedulerEmptyDayUIModel>(binding.root) {

        private val touchableSpan
            get() = SpannableStringBuilder().createTouchableSpan(
                colors[R.color.button_text_blue],
                colors[R.color.button_bg_dark_blue],
                binding.btn.context,
                R.style.TextAppearance_App_ButtonText_Default,
                {},
                "Create a schedule",
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE,
            )
                .appendExtendedImage(ImageSpan(binding.btn.context.drawables[com.aya.digital.core.baseresources.R.drawable.ic_arrow_right_blue]), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        init {
            binding.btn.movementMethod = LinkTouchMovementMethod()
            binding.btn.text = touchableSpan
            binding.btn bindClick { createScheduleClick() }
            // binding.root bindClick {onSlotClick(item.id)}
        }

        override fun bind(item: AppointmentsSchedulerEmptyDayUIModel) {
            super.bind(item)
            binding.title.text =
                binding.descr.context.strings[LocR.string.scheduler_item_empty_day_title].format(
                    item.doctorName
                )
        }


    }

}