package com.aya.digital.core.ui.base.validation

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime


interface IDatesValidator {
    fun validate(firstDate: LocalDateTime, secondDate: LocalDateTime): ValidationResult
}