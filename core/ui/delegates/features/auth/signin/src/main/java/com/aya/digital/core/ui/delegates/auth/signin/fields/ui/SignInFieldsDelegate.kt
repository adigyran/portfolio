package com.aya.digital.core.ui.delegates.auth.signin.fields.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.adapters.base.ScrolledItemPosition
import com.aya.digital.core.ui.delegates.fields.emailphone.model.EmailPhoneFieldUIModel
import com.aya.digital.core.ui.delegates.fields.emailphone.model.PasswordFieldUIModel
import com.aya.digital.core.ui.delegates.auth.signin.fields.model.SignInFieldsUIModel
import com.aya.digital.core.ui.delegates.features.auth.signin.databinding.ItemSigninFieldsBinding
import com.aya.digital.core.ui.delegates.fields.emailphone.ui.EmailPhoneDelegateListeners
import com.aya.digital.core.ui.delegates.fields.emailphone.ui.PasswordFieldDelegateListeners
import com.aya.digital.core.ui.delegates.fields.emailphone.ui.emailPhoneFieldDelegate
import com.aya.digital.core.ui.delegates.fields.emailphone.ui.passwordFieldDelegate

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun signInFieldsDelegate(
    recyclerPool: RecyclerView.RecycledViewPool,
    recyclerScrollPositions: () -> MutableMap<String, ScrolledItemPosition>,
    delegateListeners: SignInFieldsDelegateListener
) = adapterDelegateViewBinding<SignInFieldsUIModel, DiffItem, ItemSigninFieldsBinding>(
    { layoutInflater, root -> ItemSigninFieldsBinding.inflate(layoutInflater, root, false) }
) {

    val lm = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        .apply {
            initialPrefetchItemCount = 5
        }

    val fieldsAdapter = BaseDelegateAdapter.create {
       delegate { emailPhoneFieldDelegate(delegateListeners.emailPhoneField) }
       delegate { passwordFieldDelegate(delegateListeners.passwordField) }

    }


    val positionChangeListener: RecyclerView.OnScrollListener? = null

    with(binding.rvFields) {
        itemAnimator = null
        setHasFixedSize(false)
        setItemViewCacheSize(6)
        isNestedScrollingEnabled = false

        layoutManager = lm


        setRecycledViewPool(recyclerPool)

        swapAdapter(fieldsAdapter, true)
    }

    bind {
        val fields: List<DiffItem> = mutableListOf<DiffItem>().apply {
           add(EmailPhoneFieldUIModel())
           add(PasswordFieldUIModel())
        }



        fieldsAdapter.items = fields

        positionChangeListener?.let { binding.rvFields.removeOnScrollListener(it) }

      /*  val scrolledPosition = recyclerScrollPositions()["HomeGarageDelegate:${item.id}"]
        if (scrolledPosition == null) {
            lm.scrollToPosition(0)
        } else {
            lm.scrollToPositionWithOffset(scrolledPosition.first, scrolledPosition.second)
        }

        positionChangeListener =
            binding.rvPolicies.addOnPositionChangeListener { firstVisibleItemPosition, offset ->
                recyclerScrollPositions()["HomeGarageDelegate:${item.id}"] =
                    ScrolledItemPosition(
                        firstVisibleItemPosition,
                        offset
                    )
            }*/
    }
}

class SignInFieldsDelegateListener(
    val signInClick: () -> Unit,
    val emailClick: () -> Unit,
    val passwordClick: () -> Unit
)
{
    val emailPhoneField: EmailPhoneDelegateListeners
    val passwordField: PasswordFieldDelegateListeners

    init {
        emailPhoneField = EmailPhoneDelegateListeners { emailClick }
        passwordField = PasswordFieldDelegateListeners { passwordClick }
    }
}
