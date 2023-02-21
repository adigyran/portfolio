package com.aya.digital.core.ui.delegates.auth.signin.fields.model

import com.aya.digital.core.ui.adapters.base.DiffItem

class SignInFieldsUIModel : DiffItem {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is SignInFieldsUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is SignInFieldsUIModel
}