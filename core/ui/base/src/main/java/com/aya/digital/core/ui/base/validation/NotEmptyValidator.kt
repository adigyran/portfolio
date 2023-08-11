package com.aya.digital.core.ui.base.validation

open class NotEmptyValidator(
    private val errorMsgId: Int,
) : ITextValidator {
    override fun validate(text: CharSequence): ValidationResult {
        return if (text.isEmpty()) ValidationResult.Error(errorMsgId) else ValidationResult.Ok
    }
}