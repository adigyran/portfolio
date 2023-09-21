package com.aya.digital.core.feature.choosers.selectwithsearch.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.choosers.selectwithsearch.ui.SelectWithSearchView
import com.aya.digital.core.feature.choosers.selectwithsearch.ui.model.SelectWithSearchStateTransformer
import com.aya.digital.core.feature.choosers.selectwithsearch.viewmodel.SelectWithSearchChooserViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun selectWithSearchDiModule(
    parentCoordinatorEvent: CoordinatorRouter,
    param: SelectWithSearchView.Param
) = DI.Module("selectWithSearchDiModule") {

    bind<SelectWithSearchStateTransformer>() with singleton { SelectWithSearchStateTransformer(param.requestCode,instance()) }

    bind {
        scoped(CustomFragmentScope).singleton {
            SelectWithSearchChooserViewModel(parentCoordinatorEvent,instance(),param)
        }
    }
}