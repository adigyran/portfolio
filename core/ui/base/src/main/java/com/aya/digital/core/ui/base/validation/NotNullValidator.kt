package com.aya.digital.core.ui.base.validation

open class NotNullValidator(
    private val errorMsgId: Int,
) : IObjectValidator {
    override fun <T> validate(obj: T): ValidationResult {
        return if (obj == null) ValidationResult.Error(errorMsgId) else ValidationResult.Ok
    }
}