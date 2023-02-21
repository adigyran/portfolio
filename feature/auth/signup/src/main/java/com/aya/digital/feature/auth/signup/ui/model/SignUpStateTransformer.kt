package com.aya.digital.feature.auth.signup.ui.model

import android.content.Context
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.auth.signup.model.SignUpFieldsUIModel
import com.aya.digital.core.ui.delegates.fields.emailphone.model.FilledButtonUIModel
import com.aya.digital.feature.auth.signup.viewmodel.SignUpState

class SignUpStateTransformer(context : Context): BaseStateTransformer<SignUpState, SignUpUiModel>() {
    override fun invoke(state: SignUpState): SignUpUiModel =
        SignUpUiModel(
            data = mutableListOf<DiffItem>().apply {
                add(SignUpFieldsUIModel())
                add(FilledButtonUIModel())
            }
        )


}