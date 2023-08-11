package com.aya.digital.core.ui.base.validation

interface ITextsValidator {
    fun validate(textFirst: CharSequence,textSecond:CharSequence): ValidationResult
}