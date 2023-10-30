package com.aya.digital.core.data.profile

data class EmergencyContact(
    val id:Int,
    val name: String? = null,
    val phone: String? = null,
    val summary:String? = null,
    val type:EmergencyContactType? = null
)
data class EmergencyContactType(
    val id:Int,
    val name:String
)