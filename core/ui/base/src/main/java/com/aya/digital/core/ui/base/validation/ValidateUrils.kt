package com.aya.digital.core.ui.base.validation

fun String.validateField(allowEmpty: Boolean,
                         vararg validators: ITextValidator):ValidationResult {
    if (allowEmpty && this.isBlank()) return ValidationResult.Ok

    for (validator in validators) {
        val validationResult = validator.validate(this)
        if (validationResult is ValidationResult.Error) return validationResult
    }
    return ValidationResult.Ok
}

fun <T : Any?> T.validateField( vararg validators: IObjectValidator):ValidationResult{
    for (validator in validators) {
        val validationResult = validator.validate(this)
        if (validationResult is ValidationResult.Error) return validationResult
    }
    return ValidationResult.Ok
}