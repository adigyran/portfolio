package com.aya.digital.core.feature.choosers.multiselect.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.choosers.multiselect.ui.SelectWithSearchView
import com.aya.digital.core.feature.choosers.multiselect.ui.model.SelectWithSearchStateTransformer
import com.aya.digital.core.feature.choosers.multiselect.viewmodel.SelectWithSearchChooserViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun selectWithSearchDiModule(
    parentCoordinatorEvent: CoordinatorRouter,
    param: SelectWithSearchView.Param
) = DI.Module("selectWithSearchDiModule") {

    bind<SelectWithSearchStateTransformer>() with singleton { SelectWithSearchStateTransformer(instance()) }

    bind {
        scoped(CustomFragmentScope).singleton {
            SelectWithSearchChooserViewModel(parentCoordinatorEvent,instance(),param)
        }
    }
}