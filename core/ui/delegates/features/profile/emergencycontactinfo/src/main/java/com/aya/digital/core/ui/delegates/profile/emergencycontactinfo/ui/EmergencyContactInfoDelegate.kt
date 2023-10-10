package com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.profile.emergencycontact.databinding.ItemEmergencyContactInfoBinding
import com.aya.digital.core.ui.delegates.features.profile.emergencycontact.databinding.ItemEmergencyContactInfoFieldBinding
import com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model.EmergencyContactInfoUIModel


class EmergencyContactInfoDelegate(private val onClick: (id: Int) -> Unit, private val onMoreClick: (id: Int) -> Unit) :
    BaseDelegate<EmergencyContactInfoUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is EmergencyContactInfoUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<EmergencyContactInfoUIModel> {
        val binding =
            ItemEmergencyContactInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemEmergencyContactInfoBinding) :
        BaseViewHolder<EmergencyContactInfoUIModel>(binding.root) {

        var initialised = false

        private val layoutInflater = LayoutInflater.from(binding.root.context)

        private val infoFields = listOf<ItemEmergencyContactInfoFieldBinding>(
            ItemEmergencyContactInfoFieldBinding.inflate(layoutInflater)
                .apply { title.text = "Full Name" },
            ItemEmergencyContactInfoFieldBinding.inflate(layoutInflater)
                .apply { title.text = "Phone" },
            ItemEmergencyContactInfoFieldBinding.inflate(layoutInflater)
                .apply { title.text = "Summary" },
            ItemEmergencyContactInfoFieldBinding.inflate(layoutInflater)
                .apply { title.text = "Type" },
        )

        private fun createFields() {
            infoFields.forEach {
                binding.llFields.addView(it.root)
            }
        }

        init {
            createFields()
            binding.root bindClick { onClick(item.id) }
            binding.moreBtn bindClick {onMoreClick(item.id)}
        }

        override fun bind(item: EmergencyContactInfoUIModel) {
            super.bind(item)
            infoFields[0].changeValue(item.fullName)
            infoFields[1].changeValue(item.phone)
            infoFields[2].changeValue(item.summary)
            infoFields[3].changeValue(item.type)
            /*  //if(!initialised) {
                  binding.title.text = item.label
                  initialised = true
              //}
              binding.value.text = item.value*/
        }

        private fun ItemEmergencyContactInfoFieldBinding.changeValue(text: String) {
           value.text = text
        }
    }
}