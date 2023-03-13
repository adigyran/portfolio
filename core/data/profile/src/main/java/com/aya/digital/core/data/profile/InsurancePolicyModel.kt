package com.aya.digital.core.data.profile

data class InsurancePolicyModel(
    val id: Int,
    val organisationId:Int,
    var organisationName:String,
    val photoAttachment:Int,
    val number:String
)