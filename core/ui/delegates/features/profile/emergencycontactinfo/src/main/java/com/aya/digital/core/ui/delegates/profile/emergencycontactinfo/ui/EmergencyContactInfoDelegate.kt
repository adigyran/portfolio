package com.aya.digital.core.ui.delegates.profile.info.ui


import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.profile.emergencycontact.databinding.ItemEmergencyContactInfoBinding
import com.aya.digital.core.ui.delegates.profile.info.model.EmergencyContactInfoUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun emergencyContactInfoDelegate() =
    adapterDelegateViewBinding<EmergencyContactInfoUIModel, DiffItem, ItemEmergencyContactInfoBinding>(
        { layoutInflater, root ->
            ItemEmergencyContactInfoBinding.inflate(
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
