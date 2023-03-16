package com.aya.digital.core.feature.profile.generalinfo.edit.ui.contract

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract

private const val MIME_TYPE_IMAGE = "image/*"


class ProfileGeneralInfoEditAvatarImageSelectContract : ActivityResultContract<Void?, Uri?>() {


    override fun createIntent(context: Context, input: Void?): Intent {

        return Intent(MediaStore.ACTION_PICK_IMAGES).apply { type =
            MIME_TYPE_IMAGE
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        return intent.takeIf { resultCode == Activity.RESULT_OK }?.data
    }
}