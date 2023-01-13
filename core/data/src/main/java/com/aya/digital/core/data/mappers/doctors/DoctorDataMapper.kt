package com.aya.digital.core.data.mappers.doctors

import com.aya.digital.core.data.model.doctors.DoctorData
import com.aya.digital.core.network.model.response.doctors.DoctorDataResponse
import com.aya.digital.core.util.mappers.base.EntityMapper

abstract class DoctorDataMapper : EntityMapper<DoctorDataResponse, DoctorData>