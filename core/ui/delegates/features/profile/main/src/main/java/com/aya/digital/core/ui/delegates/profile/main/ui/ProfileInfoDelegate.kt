package com.aya.digital.core.ui.delegates.profile.info.ui


import android.view.View
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.localisation.R.string.*
import com.aya.digital.core.ui.delegates.features.profile.main.databinding.ItemProfileMainBinding
import com.aya.digital.core.ui.delegates.profile.info.model.ProfileMainUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun profileMainDelegate(delegateListeners: ProfileMainDelegateListeners) =
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

        binding.root bindClick {delegateListeners.onClick(item.tag)}
        bind {
            if(!initialised)
            {
                binding.icon.setImageResource(item.icon)
                binding.title.text = item.value
                initialised = true
            }
        }


    }
class ProfileMainDelegateListeners(val onClick: ((Int) -> Unit))
