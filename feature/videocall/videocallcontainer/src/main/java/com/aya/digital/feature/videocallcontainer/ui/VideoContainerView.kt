package com.aya.digital.feature.videocallcontainer.ui

import android.app.PictureInPictureParams
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Rational
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.baseresources.databinding.ViewFragmentContainerBinding
import com.aya.digital.core.ext.argument
import com.aya.digital.core.ext.createActivity
import com.aya.digital.core.ext.createFragment
import com.aya.digital.core.ext.extra
import com.aya.digital.core.ext.gone
import com.aya.digital.core.ext.toggleVisibility
import com.aya.digital.feature.videocallcontainer.di.RootNavigatorParam
import com.aya.digital.feature.videocallcontainer.di.videoContainerDiModule
import com.aya.digital.feature.videocallcontainer.navigation.VideoContainerNavigator
import com.aya.digital.feature.videocallcontainer.viewmodel.VideoContainerState
import com.aya.digital.feature.videocallcontainer.viewmodel.VideoContainerViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.navigation.AppFlavour
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.navigation.graph.DefaultRootScreenManager
import com.aya.digital.core.ui.base.screens.DiActivity
import com.aya.digital.feature.videocallcontainer.databinding.VideocallContainerViewTopLayoutBinding
import com.aya.digital.feature.videocallcontainer.ui.model.VideoContainerStateTransformer
import com.aya.digital.feature.videocallcontainer.ui.model.VideoContainerUiModel
import com.github.terrakok.cicerone.Navigator
import kotlinx.parcelize.Parcelize
import org.kodein.di.factory
import org.kodein.di.instance
import org.kodein.di.on

class VideoContainerView :
    DiActivity<ViewFragmentContainerBinding, VideoContainerViewModel, VideoContainerState, BaseSideEffect, VideoContainerUiModel, VideoContainerStateTransformer>() {

    private var param: Param by extra("param")

    private val appFlavour: AppFlavour by kodein.on(context = this).instance()

    private val defaultRootScreenManager: DefaultRootScreenManager by kodein.on(context = this)
        .instance("root_navigation")

    private lateinit var topLayoutBinding: VideocallContainerViewTopLayoutBinding

    private val viewModelFactory: ((Unit) -> VideoContainerViewModel) by kodein.on(
        context = this
    ).factory()

    private val coordinatorFactory: ((fragmentManager: FragmentManager) -> Coordinator) by kodein.on(
        context = this
    ).factory()

    private val navigatorFactory: ((param: RootNavigatorParam) -> VideoContainerNavigator) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> VideoContainerStateTransformer) by kodein.on(
        context = this
    ).factory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            openDefaultScreen()
            viewModel.listenForToken()
        }
        topLayoutBinding = VideocallContainerViewTopLayoutBinding.inflate(layoutInflater)
        binding.root.addView(
            topLayoutBinding.root,
            ViewGroup.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
        )
        topLayoutBinding.progress.gone()
    }

    private fun openDefaultScreen() {
        viewModel.openDefaultScreen()
    }

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: VideoContainerState) {
        stateTransformer(state).run {
            inProgress.run { topLayoutBinding.progress.toggleVisibility(inProgress) }
        }
    }

    override fun provideDiModule() = videoContainerDiModule(param)

    override fun provideViewBinding(inflater: LayoutInflater): ViewFragmentContainerBinding =
        ViewFragmentContainerBinding.inflate(inflater)

    override fun provideViewModel(): VideoContainerViewModel = viewModelFactory(Unit)

    override fun provideStateTransformer(): VideoContainerStateTransformer =
        stateTransformerFactory(Unit)

    override fun provideNavigator(): Navigator = navigatorFactory(
        RootNavigatorParam(
            this,
            binding.fragmentContainer.id
        )
    )


    /* override fun onBackPressed() {
         val fragment = supportFragmentManager.findFragmentById(com.aya.digital.core.baseresources.R.id.fragmentContainer)
         if ((fragment as? BackButtonListener)?.onBackPressed() == true) {
             return
         }
         localCicerone.router.exit()
     }*/
    override fun provideCoordinator(): Coordinator = coordinatorFactory(supportFragmentManager)

    @Parcelize
    class Param(
        val roomId: Int
    ) : Parcelable

    companion object {
        fun getNewInstance(
            context: Context,
            roomId: Int
        ): Intent = createActivity<VideoContainerView, Param>(context, Param(roomId))

    }

}
