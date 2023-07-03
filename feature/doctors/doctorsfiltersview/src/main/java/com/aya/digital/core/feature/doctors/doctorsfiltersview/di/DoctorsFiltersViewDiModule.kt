package com.aya.digital.core.feature.doctors.doctorsfiltersview.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.doctors.doctorsfiltersview.ui.model.DoctorsFiltersViewStateTransformer
import com.aya.digital.core.feature.doctors.doctorsfiltersview.viewmodel.DoctorsFiltersViewViewModel
import org.kodein.di.*

fun doctorsFiltersViewDiModule(
) = DI.Module("doctorsFiltersViewDiModule") {

    bind<DoctorsFiltersViewStateTransformer>() with singleton {
        DoctorsFiltersViewStateTransformer(
            instance(),
            instance()
        )
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            DoctorsFiltersViewViewModel(
                instance("parent_coordinator_bottomnav")
            )
        }
    }
}