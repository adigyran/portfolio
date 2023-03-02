package com.aya.digital.core.feature.choosers.multiselect.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.choosers.multiselect.ui.MultiSelectChooserView
import com.aya.digital.core.feature.choosers.multiselect.ui.model.MultiSelectChooserStateTransformer
import com.aya.digital.core.feature.choosers.multiselect.viewmodel.MultiSelectChooserViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun multiSelectChooserDiModule(
    parentCoordinatorEvent: CoordinatorRouter,
    param: MultiSelectChooserView.Param
) = DI.Module("multiSelectChooserDiModule") {

    bind<MultiSelectChooserStateTransformer>() with singleton { MultiSelectChooserStateTransformer(instance()) }

    bind {
        scoped(CustomFragmentScope).singleton {
            MultiSelectChooserViewModel(parentCoordinatorEvent,instance(),param)
        }
    }
}