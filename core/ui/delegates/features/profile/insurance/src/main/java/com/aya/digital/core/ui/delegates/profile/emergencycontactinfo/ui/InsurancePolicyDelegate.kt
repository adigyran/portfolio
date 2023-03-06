package com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.ui

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.profile.insurance.databinding.ItemInsurancePolicyBinding
import com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model.InsurancePolicyUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun insurancePolicyDelegate(
    onPolicyClick: (id: Int) -> Unit,
    onPolicyMoreClick: (id: Int) -> Unit
) =
    adapterDelegateViewBinding<InsurancePolicyUIModel, DiffItem, ItemInsurancePolicyBinding>(
        { layoutInflater, root ->
            ItemInsurancePolicyBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {
        var initialised = false
        bind {
            if (!initialised) {

                initialised = true
            }
        }


    }
