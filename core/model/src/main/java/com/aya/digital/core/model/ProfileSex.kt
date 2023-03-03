package com.aya.digital.core.model

import android.content.Context
import android.os.Parcelable
import com.aya.digital.core.localisation.R
import com.aya.digital.core.localisation.strings
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class ProfileSex(val tag: String, val nameId: Int) : Parcelable {
    object Male : ProfileSex("male", R.string.male_sex_name)
    object Female : ProfileSex("female", R.string.female_sex_name)
    object Other : ProfileSex("other", R.string.other_sex_name)

    companion object {
        fun getAllSexes() =
            setOf(Male, Female, Other)

        fun getSexByTag(tag: String) = getAllSexes().firstOrNull { it.tag == tag }
    }
}

fun ProfileSex.getSexName(context: Context) = context.strings[this.nameId]
