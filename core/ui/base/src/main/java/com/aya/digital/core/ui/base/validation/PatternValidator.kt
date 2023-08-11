package com.aya.digital.core.ui.base.validation

import java.util.regex.Pattern

open class PatternValidator(private val pattern: Pattern, private val errorMsgId: Int) :
    ITextValidator {
    override fun validate(text: CharSequence): ValidationResult =
        if (pattern.matcher(text.trim()).matches()) ValidationResult.Ok else ValidationResult.Error(
            errorMsgId
        )
}
