package com.aya.digital.core.data.doctors.mappers

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.doctors.DoctorData
import com.aya.digital.core.data.doctors.PatientData
import com.aya.digital.core.network.model.response.doctors.DoctorDataResponse
import com.aya.digital.core.network.model.response.patient.PatientDataResponse

abstract class PatientDataMapper : EntityMapper<PatientDataResponse, PatientData>