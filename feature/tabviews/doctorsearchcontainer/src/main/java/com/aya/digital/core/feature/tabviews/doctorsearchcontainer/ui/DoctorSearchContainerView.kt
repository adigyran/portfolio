package com.aya.digital.core.feature.tabviews.doctorsearchcontainer.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.DoctorSearchScreenTabsScreens
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.databinding.ViewDoctorsearchContainerBinding
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.di.doctorSearchContainerDiModule
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.ui.model.DoctorSearchContainerStateTransformer
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.ui.model.DoctorSearchContainerUiModel
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.viewmodel.DoctorSearchContainerSideEffects
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.viewmodel.DoctorSearchContainerState
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.viewmodel.DoctorSearchContainerViewModel
import com.aya.digital.core.ui.base.screens.DiFragment
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.instance
import org.kodein.di.on


class DoctorSearchContainerView :
    DiFragment<ViewDoctorsearchContainerBinding, DoctorSearchContainerViewModel, DoctorSearchContainerState, DoctorSearchContainerSideEffects, DoctorSearchContainerUiModel, DoctorSearchContainerStateTransformer>() {


    private val viewModelFactory: ((Unit) -> DoctorSearchContainerViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> DoctorSearchContainerStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val doctorSearchScreenTabsScreens: DoctorSearchScreenTabsScreens by kodein.on(context = this)
        .instance()


    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
        binding.btnToggleMapList bindClick { viewModel.toggleMode() }
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.adapter =
            DoctorSearchContainerPageAdapter(
                childFragmentManager,
                lifecycle,
                requireActivity(),
                doctorSearchScreenTabsScreens
            )
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)

    }


    override fun sideEffect(sideEffect: DoctorSearchContainerSideEffects) =
        when (sideEffect) {
            is DoctorSearchContainerSideEffects.Error -> processErrorSideEffect(sideEffect.error)
        }

    override fun provideDiModule(): DI.Module =
        doctorSearchContainerDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDoctorsearchContainerBinding =
        ViewDoctorsearchContainerBinding.inflate(inflater, container, false)


    override fun provideStateTransformer(): DoctorSearchContainerStateTransformer =
        stateTransformerFactory(Unit)

    override fun render(state: DoctorSearchContainerState) {
        state.currentMode.run {
            binding.viewPager.setCurrentItem(this.page, false)
        }
    }

    override fun provideViewModel(): DoctorSearchContainerViewModel = viewModelFactory(Unit)

    private class DoctorSearchContainerPageAdapter(
        private val fm: FragmentManager,
        private val lifecycle: Lifecycle,
        private val context: Context,
        val doctorSearchScreenTabsScreens: DoctorSearchScreenTabsScreens
    ) :
        FragmentStateAdapter(fm, lifecycle) {
        override fun getItemCount(): Int = 2
        override fun createFragment(position: Int): Fragment =
            doctorSearchScreenTabsScreens.getFragment(position, fm)


    }

    companion object {

        fun getNewInstance(): DoctorSearchContainerView {
            return DoctorSearchContainerView().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }

}
