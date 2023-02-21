package com.aya.digital.core.mvi

abstract class BaseStateTransformer<State : BaseState,UiModel : BaseUiModel> {
    abstract operator fun invoke(state: State): UiModel
}