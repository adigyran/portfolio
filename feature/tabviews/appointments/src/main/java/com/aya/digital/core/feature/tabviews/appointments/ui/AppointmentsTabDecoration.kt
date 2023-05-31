package com.aya.digital.core.feature.tabviews.appointments.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.AppointmentUiStatus
import com.aya.digital.core.ui.delegates.appointments.patientappointment.ui.PatientAppointmentStatusFooterDelegate
import com.aya.digital.core.ui.delegates.appointments.patientappointment.ui.PatientAppointmentStatusHeaderDelegate
import com.aya.digital.core.ui.delegates.appointments.patientappointment.ui.StatusHolder


internal class AppointmentsTabDecoration(private val context: Context) :
    RecyclerView.ItemDecoration() {

    private var decoratedLeft = 0.0F
    private var decoratedTop = 0.0F
    private var decoratedRight = 0.0F
    private var decoratedBottom = 0.0F
    private var left: Float = 0.0F
    private var top = 0F
    private var right = 0F
    private var bottom = 0F
    private val paint = Paint()
    private val scheduledColor = context.getColor(R.color.bg_scheduled_appointments)
    private val doneColor = context.getColor(R.color.bg_green)
    private val cancelledColor = context.getColor(R.color.bg_light_grey)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val viewHolder = parent.findContainingViewHolder(view)
        var horizontalDp = 12
        val horizontal = horizontalDp.dpToPx()
        val top = (18).dpToPx()
        outRect.top = top
        outRect.left = horizontal
        outRect.right = horizontal
    }


    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount;
        var currentStatus: AppointmentUiStatus = AppointmentUiStatus.CANCELLED
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 2F
        val lm = parent.layoutManager ?: return
        parent.children
            .filter { view ->
                val viewHolder = parent.findContainingViewHolder(view)
                viewHolder is PatientAppointmentStatusHeaderDelegate.ViewHolder || viewHolder is PatientAppointmentStatusFooterDelegate.ViewHolder
            }
            .groupBy { view: View ->
                val viewHolder = parent.findContainingViewHolder(view)
                (viewHolder as StatusHolder).getDelegateStatus()
            }
            .forEach { entry ->
                currentStatus = entry.key
                paint.color = when (currentStatus) {
                    AppointmentUiStatus.SCHEDULED -> scheduledColor
                    AppointmentUiStatus.CANCELLED -> cancelledColor
                    AppointmentUiStatus.DONE -> doneColor
                }
                val firstView = entry.value.first()
                val lastView = entry.value.last()
                decoratedLeft = lm.getDecoratedLeft(firstView) + firstView.translationX
                decoratedRight = lm.getDecoratedRight(firstView) + firstView.translationX
                decoratedTop = lm.getDecoratedTop(firstView) + firstView.translationY
                decoratedBottom = lm.getDecoratedBottom(lastView) + lastView.translationY
                c.drawRect(decoratedLeft, decoratedTop, decoratedRight, decoratedBottom, paint)
            }
          /*  .forEach { view ->
                val viewHolder = parent.findContainingViewHolder(view)
                decoratedLeft = lm.getDecoratedLeft(view) + view.translationX
                decoratedRight = lm.getDecoratedRight(view) + view.translationX
                if (viewHolder is PatientAppointmentStatusHeaderDelegate.ViewHolder) {
                    currentStatus = viewHolder.status
                    decoratedTop = lm.getDecoratedTop(view) + view.translationY

                } else if (viewHolder is PatientAppointmentStatusFooterDelegate.ViewHolder) {
                    decoratedBottom = lm.getDecoratedBottom(view) + view.translationY
                }
                paint.color = when (currentStatus) {
                    AppointmentUiStatus.SCHEDULED -> scheduledColor
                    AppointmentUiStatus.CANCELLED -> cancelledColor
                    AppointmentUiStatus.DONE -> doneColor
                }

                c.drawRect(decoratedLeft, decoratedTop, decoratedRight, decoratedBottom, paint)
            }*/


        /*  .map { parent.findContainingViewHolder(it) }
          .filter { viewHolder -> viewHolder is PatientAppointmentStatusHeaderDelegate.ViewHolder || viewHolder is PatientAppointmentStatusFooterDelegate.ViewHolder }
          .forEach {
              viewHolder -> viewHolder?.itemView
          }*/
        /* lm?.let {
            for (i in 0..childCount) {
                val view = parent.getChildAt(i)
                view?.let {
                    val viewHolder = view.let { parent.findContainingViewHolder(it) }
                    if (viewHolder is StatusHolder) {
                        currentStatus = viewHolder.getDelegateStatus()
                    }
                    decoratedLeft = lm.getDecoratedLeft(view) + view.translationX
                    decoratedTop = lm.getDecoratedTop(view) + view.translationY
                    decoratedRight = lm.getDecoratedRight(view) + view.translationX
                    decoratedBottom = lm.getDecoratedBottom(view) + view.translationY
                    paint.color =  when(currentStatus)
                    {
                        AppointmentUiStatus.SCHEDULED -> scheduledColor
                        AppointmentUiStatus.CANCELLED -> cancelledColor
                        AppointmentUiStatus.DONE -> doneColor
                    }
                    paint.style = Paint.Style.FILL
                    paint.strokeWidth = 2F
                    c.drawRect(decoratedLeft, decoratedTop, decoratedRight, decoratedBottom, paint)
                }
            }
        }*/
    }
}