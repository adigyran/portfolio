package com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.ui

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.profile.insurance.databinding.ItemInsurancePolicyPhotoBinding
import com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model.InsurancePolicyPhotoUIModel
import com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model.InsurancePolicyUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun insurancePolicyPhotoDelegate(onPhotoClick: () -> Unit, onPhotoMoreClick: () -> Unit) =
    adapterDelegateViewBinding<InsurancePolicyPhotoUIModel, DiffItem, ItemInsurancePolicyPhotoBinding>(
        { layoutInflater, root ->
            ItemInsurancePolicyPhotoBinding.inflate(
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
