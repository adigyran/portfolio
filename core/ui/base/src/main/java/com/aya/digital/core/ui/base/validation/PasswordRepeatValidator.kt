package com.aya.digital.core.ui.base.validation

import java.util.regex.Pattern

class PasswordRepeatValidator(errorMsgId: Int) : TextsValidator(
    comparator = {charSequence, charSequence2 -> charSequence2.isNotBlank() && charSequence == charSequence2 },
    errorMsgId = errorMsgId
)