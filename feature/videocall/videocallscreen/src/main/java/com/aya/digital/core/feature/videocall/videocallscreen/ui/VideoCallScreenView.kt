package com.aya.digital.core.feature.videocall.videocallscreen.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.argument
import com.aya.digital.core.ext.createFragment
import com.aya.digital.core.feature.videocall.videocallscreen.databinding.ViewVideocallScreenBinding
import com.aya.digital.core.feature.videocall.videocallscreen.di.videoCallScreenDiModule
import com.aya.digital.core.feature.videocall.videocallscreen.ui.model.VideoCallScreenStateTransformer
import com.aya.digital.core.feature.videocall.videocallscreen.ui.model.VideoCallScreenUiModel
import com.aya.digital.core.feature.videocall.videocallscreen.viewmodel.VideoCallScreenState
import com.aya.digital.core.feature.videocall.videocallscreen.viewmodel.VideoCallScreenViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.base.screens.DiFragment
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class VideoCallScreenView :
    DiFragment<ViewVideocallScreenBinding, VideoCallScreenViewModel, VideoCallScreenState, BaseSideEffect, VideoCallScreenUiModel, VideoCallScreenStateTransformer>() {

    private var param: Param by argument("param")

    private val viewModelFactory: ((Unit) -> VideoCallScreenViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> VideoCallScreenStateTransformer) by kodein.on(
        context = this
    ).factory()

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)

    }

    override fun provideDiModule(): DI.Module = videoCallScreenDiModule(tryTyGetParentRouter(),param)

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewVideocallScreenBinding =
        ViewVideocallScreenBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: VideoCallScreenState) {
        stateTransformer(state).run {

        }
    }

    @Parcelize
    class Param(
        val doctorId: Int
    ) : Parcelable

    override fun provideViewModel(): VideoCallScreenViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): VideoCallScreenStateTransformer =
        stateTransformerFactory(Unit)

    companion object {
        fun getNewInstance(doctorId: Int): VideoCallScreenView =
            createFragment(
                Param(doctorId)
            )
    }
}
