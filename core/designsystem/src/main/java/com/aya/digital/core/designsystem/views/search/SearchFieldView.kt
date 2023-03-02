package com.aya.digital.core.designsystem.views.search

import android.content.Context

import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.aya.digital.core.designsystem.databinding.SearchViewFieldBinding
import com.google.android.material.search.SearchView

class SearchFieldView : LinearLayout {

    var searchView : SearchView

    var binding: SearchViewFieldBinding =
        SearchViewFieldBinding.inflate(LayoutInflater.from(context),this)


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        searchView = binding.searchField
    }
}