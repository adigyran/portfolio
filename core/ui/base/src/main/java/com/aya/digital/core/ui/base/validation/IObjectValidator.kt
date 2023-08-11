package com.aya.digital.core.ui.base.validation

interface IObjectValidator {
    fun <T : Any?> validate(obj: T): ValidationResult
}