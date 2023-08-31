package com.aya.digital.core.domain.base.models.doctors

data class DoctorPaginationModel(val totalItems:Int=0,val cursor:String?, val doctors:List<DoctorModel>)
