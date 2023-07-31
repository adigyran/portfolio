package com.aya.digital.feature.bottomdialogs.codedialog.ui

import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.aya.digital.core.ext.argument
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.createFragment
import com.aya.digital.feature.bottomdialogs.codedialog.di.codeDialogDiModule
import com.aya.digital.feature.bottomdialogs.codedialog.viewmodel.CodeDialogState
import com.aya.digital.feature.bottomdialogs.codedialog.viewmodel.CodeDialogViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.base.screens.DiBottomSheetDialogFragment
import com.aya.digital.feature.bottomdialogs.codedialog.databinding.ViewCodeBottomDialogBinding
import com.aya.digital.feature.bottomdialogs.codedialog.ui.model.CodeDialogStateTransformer
import com.aya.digital.feature.bottomdialogs.codedialog.ui.model.CodeDialogUiModel
import com.mukesh.OTP_VIEW_TYPE_BORDER
import com.mukesh.OtpView
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on
import kotlin.properties.Delegates

class CodeDialogView :
    DiBottomSheetDialogFragment<ViewCodeBottomDialogBinding, CodeDialogViewModel, CodeDialogState, BaseSideEffect, CodeDialogUiModel, CodeDialogStateTransformer>() {

    private var param: Param by argument("param")

    private var initialised: Boolean = false

    private val viewModelFactory: ((Unit) -> CodeDialogViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> CodeDialogStateTransformer) by kodein.on(
        context = this
    ).factory()

    override fun provideViewModel(): CodeDialogViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): CodeDialogStateTransformer =
        stateTransformerFactory(Unit)


    override fun provideDiModule(): DI.Module = codeDialogDiModule(tryTyGetParentRouter(), param)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var code: String by Delegates.observable("") {
            prop, old, new ->
        println("$old -> $new")
        viewModel.codeChanged(new)
    }

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
       // binding.otpView.setOtpCompletionListener(viewModel::codeChanged)
        binding.otpView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                var otpValue by remember { mutableStateOf("") }
                MaterialTheme {
                    OtpView(
                        otpText = otpValue,
                        onOtpTextChange = {
                            otpValue = it
                            code = it
                        },

                        type = OTP_VIEW_TYPE_BORDER,
                        otpCount = 6,
                        containerSize = 48.dp,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        charColor = Color.Black
                    )
                }
            }
        }
        binding.btnClose bindClick {viewModel.close()}
        binding.sendBtn bindClick {viewModel.sendCode()}
    }

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewCodeBottomDialogBinding = ViewCodeBottomDialogBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: CodeDialogState) {
        val uiModel = stateTransformer(state)
        if (!initialised) {
            initialised = true
            binding.titleTv.text = uiModel.title
            binding.bottomDescriptionTv.text = uiModel.bottomDescription
            binding.descriptionTv.text = uiModel.description
        }
    }


    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }

    @Parcelize
    class Param(
        val requestCode: String,
        val value: String
    ) : Parcelable

    companion object {
        fun getNewInstance(requestCode: String, value: String): CodeDialogView =
            createFragment(Param(requestCode, value))
    }
}
