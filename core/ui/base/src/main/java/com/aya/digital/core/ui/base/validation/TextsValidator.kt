package com.aya.digital.core.ui.base.validation

import java.util.regex.Pattern

open class TextsValidator(
    private val comparator: (CharSequence, CharSequence) -> Boolean,
    private val errorMsgId: Int
) :
    ITextsValidator {

    override fun validate(textFirst: CharSequence, textSecond: CharSequence): ValidationResult =
        if (comparator(textFirst, textSecond)) ValidationResult.Ok else ValidationResult.Error(
            errorMsgId
        )
}
