package com.aya.digital.core.model

import android.content.Context
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.aya.digital.core.localisation.R as LocalisationR

@Parcelize
sealed class ProfileSex(val tag: String, val nameId: Int) : Parcelable {
    object Male : ProfileSex("male", LocalisationR.string.male_sex_name)
    object Female : ProfileSex("female", LocalisationR.string.female_sex_name)
    object Other : ProfileSex("other", LocalisationR.string.other_sex_name)

    companion object {
        fun getAllSexes() =
            setOf(Male, Female, Other)

        fun getSexByTag(tag: String) = getAllSexes().firstOrNull { it.tag == tag }
        fun getSexByName(name: String) =
            getAllSexes().firstOrNull { it.tag.lowercase() == name.lowercase() }
        fun getSexByName(context: Context,name: String) =
            getAllSexes().firstOrNull { it.getSexName(context).lowercase() == name.lowercase() }
    }
}

fun ProfileSex.getSexName(context: Context) = context.getString(this.nameId)
