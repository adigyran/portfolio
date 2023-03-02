package com.aya.digital.core.ui.delegates.profile.info.ui


import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.localisation.R.string.*
import com.aya.digital.core.ui.delegates.features.profile.main.databinding.ItemProfileMainBinding
import com.aya.digital.core.ui.delegates.profile.info.model.ProfileMainUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun profileMainDelegate() =
    adapterDelegateViewBinding<ProfileMainUIModel, DiffItem, ItemProfileMainBinding>(
        { layoutInflater, root ->
            ItemProfileMainBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {
        var initialised = false

        bind {
            if(!initialised)
            {
                binding.icon.setImageResource(item.icon)
                binding.title.text = item.value
                initialised
            }
        }


    }
