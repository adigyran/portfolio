package com.aya.digital.core.feature.choosers.selectwithsearch.ui.model

import com.aya.digital.core.localisation.R
import com.aya.digital.core.util.requestcodes.RequestCodes

internal object SelectWithSearchTitleSelector {
    fun getTitle(requestCode:String) = when(requestCode)
    {
        RequestCodes.INSURANCE_LIST_REQUEST_CODE -> R.string.multiselect_insurances_title
        RequestCodes.LOCATIONS_LIST_REQUEST_CODE -> R.string.multiselect_location_title
        RequestCodes.SPECIALITIES_LIST_REQUEST_CODE -> R.string.multiselect_speciality_title
        RequestCodes.MEDICAL_DEGREE_LIST_REQUEST_CODE -> R.string.multiselect_speciality_title
        RequestCodes.LANGUAGES_LIST_REQUEST_CODE -> R.string.multiselect_languages_title
        RequestCodes.EMERGENCY_CONTACT_TYPE_LIST_REQUEST_CODE -> R.string.multiselect_emergency_contact_types_title
        else -> R.string.multiselect_empty_title
    }
}