package com.aya.digital.core.feature.profile.generalinfo.edit

import com.aya.digital.core.util.requestcodes.RequestCodes

object FieldsTags {
    const val FIRST_NAME_FIELD_TAG = 515641
    const val LAST_NAME_FIELD_TAG = 454555
    const val MIDDLE_NAME_FIELD_TAG = 123123
    const val BIRTH_DATE_FIELD_TAG = 68664
    const val SEX_FIELD_TAG = 4565754
    const val HEIGHT_FIELD_TAG = 1655165
    const val WEIGHT_FIELD_TAG = 3129545
    const val SHORT_ADDRESS_FIELD_TAG = 2105840
    const val SSN_OR_TIN_FIELD_TAG = 35432125
    const val NPI_FIELD_TAG = 4643642
    const val TIN_FIELD_TAG = 54984919
    const val LICENSE_NUMBER_FIELD_TAG = 563222
    const val LANGUAGES_FIELD_TAG = 5667787
    const val MEDICAL_DEGREES_FIELD_TAG = 3453223
    const val SPECIALISATIONS_FIELD_TAG = 87842324
    const val BIO_FIELD_TAG = 585858585
}

internal fun Int.getRequestCodeForSelectionField() = when(this)
{
    FieldsTags.LANGUAGES_FIELD_TAG -> RequestCodes.LANGUAGES_LIST_REQUEST_CODE
    FieldsTags.MEDICAL_DEGREES_FIELD_TAG -> RequestCodes.MEDICAL_DEGREE_LIST_REQUEST_CODE
    FieldsTags.SPECIALISATIONS_FIELD_TAG -> RequestCodes.SPECIALITIES_LIST_REQUEST_CODE
    else -> RequestCodes.ERROR_CODE
}