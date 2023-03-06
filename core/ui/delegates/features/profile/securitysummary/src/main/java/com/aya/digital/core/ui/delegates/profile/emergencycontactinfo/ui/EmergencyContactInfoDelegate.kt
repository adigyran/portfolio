package com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.ui


import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.profile.securitysummary.databinding.ItemSecuritySummaryBinding
import com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model.SecuritySummaryUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun securitySummaryDelegate() =
    adapterDelegateViewBinding<SecuritySummaryUIModel, DiffItem, ItemSecuritySummaryBinding>(
        { layoutInflater, root ->
            ItemSecuritySummaryBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {
        var initialised = false
        bind {
            if(!initialised) {
                binding.title.text = item.label
                initialised = true
            }
            binding.value.text = item.value
        }


    }
