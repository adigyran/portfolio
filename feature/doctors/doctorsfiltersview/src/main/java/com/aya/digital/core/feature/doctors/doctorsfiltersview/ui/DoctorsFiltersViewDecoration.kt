package com.aya.digital.core.feature.doctors.doctorsfiltersview.ui

import android.graphics.Canvas
import androidx.recyclerview.widget.RecyclerView

internal class DoctorsFiltersViewDecoration : RecyclerView.ItemDecoration() {

    private data class HorizontalSpacings(val left: Int, val right: Int)

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
    }
}