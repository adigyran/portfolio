package com.aya.digital.core.feature.doctors.doctorsfiltersview.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.doctors.doctorsfiltersview.ui.DoctorsFiltersView
import com.aya.digital.core.feature.doctors.doctorsfiltersview.ui.model.DoctorsFiltersViewStateTransformer
import com.aya.digital.core.feature.doctors.doctorsfiltersview.viewmodel.DoctorCardViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
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
            DoctorCardViewModel(
                instance("parent_coordinator_bottomnav")
            )
        }
    }
}