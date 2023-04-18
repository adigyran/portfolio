package com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.colors
import com.aya.digital.core.ext.gone
import com.aya.digital.core.ext.visible
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.base.ext.createTouchableSpan
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.DoctorDetailsBioUIModel
import com.aya.digital.core.ui.delegates.features.doctorcard.doctordetails.databinding.ItemDoctorDetailsBioBinding
import timber.log.Timber

class DoctorDetailsBioDelegate(val bioReadMoreClicked: () -> Unit) :
    BaseDelegate<DoctorDetailsBioUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is DoctorDetailsBioUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<DoctorDetailsBioUIModel> {
        val binding =
            ItemDoctorDetailsBioBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemDoctorDetailsBioBinding) :
        BaseViewHolder<DoctorDetailsBioUIModel>(binding.root) {
        val MAX_LINES = 3
        private var firstRun = true
        private var readMoreFlag = false
        private val touchableSpan
            get() = SpannableStringBuilder().createTouchableSpan(
                colors[R.color.button_text_blue],
                colors[R.color.button_bg_dark_blue],
                binding.tvText.context,
                R.style.TextAppearance_App_ButtonText_Default,
                {},
                "Read more",
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE,
                )

        init {
            binding.tvText.movementMethod = LinkTouchMovementMethod()
            binding.tvReadMore bindClick { bioReadMoreClicked() }
            binding.tvReadMore.text = touchableSpan
        }


        override fun bind(item: DoctorDetailsBioUIModel) {
            super.bind(item)
            if (firstRun) {
                binding.tvText.text = item.bio
                binding.tvText.post {
                    if (binding.tvText.lineCount > MAX_LINES) {
                        resetTextView()
                        binding.tvReadMore.visible()
                        readMoreFlag = true
                    } else {
                        binding.tvText.ellipsize = null
                        binding.tvText.maxLines = Int.MAX_VALUE
                        binding.tvReadMore.gone()
                    }
                }
                firstRun = false
            } else if (readMoreFlag) {
                if (item.isExpanded) {
                    binding.tvText.ellipsize = null
                    binding.tvText.maxLines = Int.MAX_VALUE
                } else {
                    resetTextView()
                }
            } else {
                resetTextView()
            }

        }

        private fun resetTextView() {
            binding.tvText.maxLines = MAX_LINES
            binding.tvText.ellipsize = TextUtils.TruncateAt.END
        }
    }
}