package com.aya.digital.core.ui.base.validation

import java.util.regex.Pattern

class PasswordValidator(errorMsgId: Int) : PatternValidator(
    Pattern.compile("^[a-zA-Z0-9~!@#$%^&*()_+`\\-={}\\[\\]:;<>./\\\\]{6,32}$"),
    errorMsgId
)