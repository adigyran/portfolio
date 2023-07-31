package com.aya.digital.core.ui.delegates.components.fields.emailphone.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.adapters.base.FieldItem
import com.aya.digital.core.ui.base.masks.CommonMasks
import ru.tinkoff.decoro.MaskImpl

class EmailPhoneFieldUIModel(val tag: Int,label: String, text: String?, error: String?,val mode:EmailPhoneFieldMode = EmailPhoneFieldMode.PHONE_MODE) : FieldItem(
    label, text,
    error
) {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        super.areItemsTheSame(newItem) && newItem is EmailPhoneFieldUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        super.areContentsTheSame(newItem) && newItem is EmailPhoneFieldUIModel
                && this.tag == newItem.tag

    val mask: MaskImpl? // property type is optional since it can be inferred from the getter's return type
        get() = when(this.mode){
            EmailPhoneFieldMode.EMAIL_MODE -> null
            EmailPhoneFieldMode.PHONE_MODE -> {
                CommonMasks.getUsPhoneValidator()
            }
        }
}

enum class EmailPhoneFieldMode()
{
    EMAIL_MODE,
    PHONE_MODE
}