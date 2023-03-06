package com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.ui


import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.profile.clinicinfo.databinding.ItemClinicInfoBinding
import com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model.ClinicInfoUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun clinicInfoDelegate() =
    adapterDelegateViewBinding<ClinicInfoUIModel, DiffItem, ItemClinicInfoBinding>(
        { layoutInflater, root ->
            ItemClinicInfoBinding.inflate(
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
