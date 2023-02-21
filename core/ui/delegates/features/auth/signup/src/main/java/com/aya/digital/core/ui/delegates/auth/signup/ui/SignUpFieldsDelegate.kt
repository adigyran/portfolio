package com.aya.digital.core.ui.delegates.fields.emailphone.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.adapters.base.ScrolledItemPosition
import com.aya.digital.core.ui.delegates.auth.signup.model.SignUpFieldsUIModel
import com.aya.digital.core.ui.delegates.features.auth.signup.databinding.ItemSignupFieldsBinding
import com.aya.digital.core.ui.delegates.fields.emailphone.model.EmailPhoneFieldUIModel
import com.aya.digital.core.ui.delegates.fields.emailphone.model.PasswordFieldUIModel
import com.aya.digital.core.ui.delegates.fields.name.model.NameFieldUIModel
import com.aya.digital.core.ui.delegates.fields.name.ui.NameFieldDelegateListeners
import com.aya.digital.core.ui.delegates.fields.name.ui.nameFieldDelegate
import com.aya.digital.core.ui.delegates.fields.selection.model.SelectionFieldUIModel
import com.aya.digital.core.ui.delegates.fields.selection.ui.SelectionFieldDelegateListeners
import com.aya.digital.core.ui.delegates.fields.selection.ui.selectionFieldDelegate

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun signUpFieldsDelegate(
    recyclerPool: RecyclerView.RecycledViewPool,
    recyclerScrollPositions: () -> MutableMap<String, ScrolledItemPosition>,
    delegateListeners: SignInFieldsDelegateListener
) = adapterDelegateViewBinding<SignUpFieldsUIModel, DiffItem, ItemSignupFieldsBinding>(
    { layoutInflater, root -> ItemSignupFieldsBinding.inflate(layoutInflater, root, false) }
) {

    val lm = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        .apply {
            initialPrefetchItemCount = 5
        }

    val fieldsAdapter = BaseDelegateAdapter.create {
       delegate { emailPhoneFieldDelegate(delegateListeners.emailPhoneField) }
       delegate { passwordFieldDelegate(delegateListeners.passwordField) }
       delegate { nameFieldDelegate(delegateListeners.nameField) }
       delegate { selectionFieldDelegate(delegateListeners.selectionField) }

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
           add(NameFieldUIModel())
           add(NameFieldUIModel())
           add(EmailPhoneFieldUIModel())
           add(SelectionFieldUIModel())
           add(PasswordFieldUIModel())
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
    val passwordClick: () -> Unit,
    val nameClick: () -> Unit,
    val selectionClick: () -> Unit
)
{
    val selectionField: SelectionFieldDelegateListeners
    val nameField: NameFieldDelegateListeners
    val emailPhoneField: EmailPhoneDelegateListeners
    val passwordField: PasswordFieldDelegateListeners

    init {
        emailPhoneField = EmailPhoneDelegateListeners { emailClick }
        passwordField = PasswordFieldDelegateListeners { passwordClick }
        nameField = NameFieldDelegateListeners { nameClick }
        selectionField = SelectionFieldDelegateListeners { selectionClick }
    }
}
