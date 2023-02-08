package com.aya.digital.core.data.doctors.mappers

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.doctors.DoctorData
import com.aya.digital.core.network.model.response.doctors.DoctorDataResponse

abstract class DoctorDataMapper : EntityMapper<DoctorDataResponse, DoctorData>