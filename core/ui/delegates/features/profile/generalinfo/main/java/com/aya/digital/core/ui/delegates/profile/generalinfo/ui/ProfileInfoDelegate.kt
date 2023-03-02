package com.aya.digital.core.ui.delegates.profile.info.ui


import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.localisation.R.string.*
import com.aya.digital.core.ui.delegates.profile.info.model.ProfileInfoUIModel
import com.aya.digital.core.ui.delegates.features.profile.databinding.ItemProfileInfoBinding
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
        bind {
            binding.title.text = item.label
            binding.value.text = item.value
        }


    }
