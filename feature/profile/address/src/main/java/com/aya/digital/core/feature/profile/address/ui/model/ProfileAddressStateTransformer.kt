package com.aya.digital.core.feature.profile.address.ui.model

import android.content.Context
import com.aya.digital.core.domain.profile.address.model.PlacePredictionModel
import com.aya.digital.core.feature.profile.address.viewmodel.ProfileAddressState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.profile.insurance.model.AutocompleteAddressSuggestionUIModel

class ProfileAddressStateTransformer(context: Context) :
    BaseStateTransformer<ProfileAddressState, ProfileAddressUiModel>() {
    override fun invoke(state: ProfileAddressState): ProfileAddressUiModel =
        ProfileAddressUiModel(
            currentAddress = state.selectedAddress,
            selectedAddressLoc = state.selectedLocation,
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    state.currentPredictions?.run {
                        addAll(
                            map {
                                AutocompleteAddressSuggestionUIModel(
                                    id = it.id,
                                    primaryText = it.getAddressText(),
                                    secondaryText = it.secondaryText
                                )
                            }
                        )
                    }
                }
            },
        )

    private fun PlacePredictionModel.getAddressText() = this.primaryText.ifBlank { this.formattedString }


}