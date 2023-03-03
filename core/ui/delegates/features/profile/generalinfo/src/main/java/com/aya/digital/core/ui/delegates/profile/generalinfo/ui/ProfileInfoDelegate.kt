package com.aya.digital.core.ui.delegates.profile.info.ui


import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.profile.generalinfo.databinding.ItemProfileInfoBinding
import com.aya.digital.core.ui.delegates.profile.info.model.ProfileInfoUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun profileInfoDelegate() =
    adapterDelegateViewBinding<ProfileInfoUIModel, DiffItem, ItemProfileInfoBinding>(
        { layoutInflater, root ->
            ItemProfileInfoBinding.inflate(
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
                binding.value.text = item.value
                initialised = true
            }
        }


    }
