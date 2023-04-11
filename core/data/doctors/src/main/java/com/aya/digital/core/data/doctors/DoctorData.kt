package com.aya.digital.core.data.doctors

import kotlinx.datetime.LocalDate

data class DoctorData(
   val id:Int,
   val firstName:String,
   val lastName:String,
   val middleName:String?,
   val avatarPhotoLink:String?,
   val bio:String,
   val city: String,
   val address:String,
   val postalCode:String,
   val specialities:List<Speciality>,
   val insurances:List<Insurance>,
   val clinics: List<Clinic>,
   val location:Location?
)
