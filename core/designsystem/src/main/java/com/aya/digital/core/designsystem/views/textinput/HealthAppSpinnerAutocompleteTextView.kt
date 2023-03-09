package com.aya.digital.core.designsystem.views.textinput

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.AppCompatAutoCompleteTextView

class HealthAppSpinnerAutocompleteTextView(context: Context, attrs: AttributeSet?) :
    AppCompatAutoCompleteTextView(context, attrs) {


    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        //if(focused) performFiltering("",0)
    }

    override fun performFiltering(text: CharSequence?, keyCode: Int) {
       // super.performFiltering(text, keyCode)
    }
}