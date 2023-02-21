package com.aya.digital.core.feature.auth.signin.ui.model

import android.content.Context
import com.aya.digital.core.feature.auth.signin.viewmodel.SignInState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.fields.emailphone.model.FilledButtonUIModel
import com.aya.digital.core.ui.delegates.auth.signin.fields.model.SignInFieldsUIModel

class SignInStateTransformer(context : Context): BaseStateTransformer<SignInState, SignInUiModel>() {
    override fun invoke(state: SignInState): SignInUiModel =
        SignInUiModel(
            data = mutableListOf<DiffItem>().apply {
                add(SignInFieldsUIModel())
                add(FilledButtonUIModel())
            }
        )


}