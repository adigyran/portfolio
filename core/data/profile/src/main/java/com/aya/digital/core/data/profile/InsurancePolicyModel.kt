package com.aya.digital.core.data.profile

data class InsurancePolicyModel(
    val id: Int,
    val organisationId:Int,
    var organisationName:String?=null,
    val photoAttachment:Int,
    var attachment:Boolean?=null,
    val number:String
)