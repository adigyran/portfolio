package com.aya.digital.core.data.base.result.models.dictionaries

data class MultiSelectResultModel(val selectedItems : Set<SelectedItem>)
data class SelectedItem(val id:Int, val text:String)
