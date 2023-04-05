package com.aya.digital.core.ui.base.masks

import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.slots.Slot
import ru.tinkoff.decoro.slots.SlotValidators

object CommonMasks {
    fun getSSNValidator(): MaskImpl {
        val slots = arrayListOf<Slot>(
            Slot(null, SlotValidators.DigitValidator()),
            Slot(null, SlotValidators.DigitValidator()),
            Slot(null, SlotValidators.DigitValidator()),
            PredefinedSlots.hardcodedSlot('-').withTags(Slot.TAG_DECORATION),
            Slot(null, SlotValidators.DigitValidator()),
            Slot(null, SlotValidators.DigitValidator()),
            PredefinedSlots.hardcodedSlot('-').withTags(Slot.TAG_DECORATION),
            Slot(null, SlotValidators.DigitValidator()),
            Slot(null, SlotValidators.DigitValidator()),
            Slot(null, SlotValidators.DigitValidator()),
            Slot(null, SlotValidators.DigitValidator()),
        )
        return MaskImpl.createTerminated(slots.toTypedArray())
            .apply { this.isShowingEmptySlots = true }
    }
}