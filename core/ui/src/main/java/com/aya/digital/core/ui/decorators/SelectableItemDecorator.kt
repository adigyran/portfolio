package com.aya.digital.core.ui.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.ivan.core.ext.dpToPx
import ru.spider.keyauto.ui.delegates.CarInfoInsuranceDelegate
import ru.spider.keyauto.ui.delegates.carinfo.CarInfoButtonsDelegate
import ru.spider.keyauto.ui.delegates.carinfo.CarInfoServiceItemDelegate
import ru.spider.keyauto.ui.delegates.carinfo.CarInfoTableFieldDelegate
import ru.spider.keyauto.ui.delegates.carinfo.CarInfoVisitHistoryDelegate

class SelectableItemDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {




        val start = (20).dpToPx()
        val end = (20).dpToPx()
        outRect.left = start
        outRect.right = end
        /*val viewHolder = parent.findContainingViewHolder(view)
        val position = parent.getChildAdapterPosition(view)

        val prevViewHolder =
            if (position == 0) null else parent.adapter?.getItemViewType(position - 1)

        val top = when {
          *//*  prevViewHolder == 2 && viewHolder is HomeRequestsDelegate.ViewHolder -> (-5).dpToPx()
                prevViewHolder == 1 && viewHolder is HomeGarageDelegate.ViewHolder -> (-5).dpToPx()
                prevViewHolder == 0 && viewHolder is NotificationSummaryDelegate.ViewHolder -> (-22).dpToPx()
                prevViewHolder == 3 && viewHolder is ConciergeServiceDelegate.ViewHolder -> 8.dpToPx()
                prevViewHolder == 4 && viewHolder is ContactNewsDelegate.ViewHolder -> 8.dpToPx()*//*
                else -> 0
            }
*/


    }
}