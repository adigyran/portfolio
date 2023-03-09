package com.aya.digital.feature.auth.chooser.ui.model

import android.content.Context
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.auth.chooser.buttons.model.ChooserButtonsUIModel
import com.aya.digital.core.ui.delegates.auth.chooser.description.model.ChooserDescriptionUIModel
import com.aya.digital.feature.auth.chooser.viewmodel.AuthChooserState

internal class AuthChooserStateTransformer(context : Context): BaseStateTransformer<AuthChooserState, AuthChooserUiModel>() {
    override fun invoke(state: AuthChooserState): AuthChooserUiModel =
        AuthChooserUiModel(
            data = mutableListOf<DiffItem>().apply {
                add(ChooserDescriptionUIModel())
                add(ChooserButtonsUIModel())
            }
        )


}