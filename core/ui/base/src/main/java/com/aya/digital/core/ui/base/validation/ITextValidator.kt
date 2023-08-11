package com.aya.digital.core.ui.base.validation

interface ITextValidator {
    fun validate(text: CharSequence): ValidationResult
}