package com.aya.digital.core.ui.delegates.auth.signup.model

import com.aya.digital.core.ui.adapters.base.DiffItem

class SignUpFieldsUIModel : DiffItem {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is SignUpFieldsUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is SignUpFieldsUIModel
}