package com.aya.digital.core.mappers.di

import com.aya.digital.core.data.mappers.appointment.AppointmentMapper
import com.aya.digital.core.data.mappers.appointment.AppointmentSlotMapper
import com.aya.digital.core.data.mappers.appointment.PatientMapper
import com.aya.digital.core.data.mappers.appointment.PractitionerMapper
import com.aya.digital.core.data.mappers.doctors.DoctorDataMapper
import com.aya.digital.core.data.mappers.doctors.LocationMapper
import com.aya.digital.core.data.mappers.doctors.PractitionersMapper
import com.aya.digital.core.data.mappers.doctors.SpecialityMapper
import com.aya.digital.core.data.mappers.preferences.AuthUserDataMapper
import com.aya.digital.core.data.mappers.profile.*
import com.aya.digital.core.data.mappers.profile.patient.PatientProfileMapper
import com.aya.digital.core.data.mappers.profile.practitioner.PractitionerProfileMapper
import com.aya.digital.core.data.mappers.schedule.ScheduleMapper
import com.aya.digital.core.data.mappers.schedule.ScheduleSlotMapper
import com.aya.digital.core.mappers.impl.appointment.AppointmentMapperImpl
import com.aya.digital.core.mappers.impl.appointment.AppointmentSlotMapperImpl
import com.aya.digital.core.mappers.impl.appointment.PatientMapperImpl
import com.aya.digital.core.mappers.impl.appointment.PractitionerMapperImpl
import com.aya.digital.core.mappers.impl.doctors.DoctorDataMapperImpl
import com.aya.digital.core.mappers.impl.doctors.LocationMapperImpl
import com.aya.digital.core.mappers.impl.doctors.PractitionersMapperImpl
import com.aya.digital.core.mappers.impl.doctors.SpecialityMapperImpl
import com.aya.digital.core.mappers.impl.preferences.AuthUserDataMapperImpl
import com.aya.digital.core.mappers.impl.profile.*
import com.aya.digital.core.mappers.impl.profile.patient.PatientProfileMapperImpl
import com.aya.digital.core.mappers.impl.profile.practitioner.PractitionerProfileMapperImpl
import com.aya.digital.core.mappers.impl.schedule.ScheduleMapperImpl
import com.aya.digital.core.mappers.impl.schedule.ScheduleSlotMapperImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

fun mappersDiModule() = DI.Module("mappersDiModule") {
    //profile mappers

    bind<PractitionerProfileMapper>() with provider { PractitionerProfileMapperImpl() }
    bind<PatientProfileMapper>() with provider { PatientProfileMapperImpl() }

    bind<MessageMapper>() with provider { MessageMapperImpl() }
    bind<RoleMapper>() with provider { RoleMapperImpl() }
    bind<LoginResultMapper>() with provider { LoginResultMapperImpl() }
    bind<EmergencyContactMapper>() with provider { EmergencyContactMapperImpl() }
    bind<ImageUploadResultMapper>() with provider { ImageUploadResultMapperImpl() }

    bind<CurrentProfileMapper>() with provider { CurrentProfileMapperImpl(instance()) }

    //appointment mappers
    bind<PractitionerMapper>() with provider { PractitionerMapperImpl() }
    bind<PatientMapper>() with provider { PatientMapperImpl() }
    bind<AppointmentSlotMapper>() with provider { AppointmentSlotMapperImpl() }
    bind<AppointmentMapper>() with provider {
        AppointmentMapperImpl(
            instance(), instance(), instance()
        )
    }

    //doctors mappers
    bind<SpecialityMapper>() with provider { SpecialityMapperImpl() }
    bind<LocationMapper>() with provider { LocationMapperImpl() }
    bind<DoctorDataMapper>() with provider { DoctorDataMapperImpl(instance(), instance()) }
    bind<PractitionersMapper>() with provider { PractitionersMapperImpl(instance()) }

    //preferences mappers
    bind<AuthUserDataMapper>() with provider { AuthUserDataMapperImpl() }

    //schedule mappers
    bind<ScheduleSlotMapper>() with provider { ScheduleSlotMapperImpl() }
    bind<ScheduleMapper>() with provider { ScheduleMapperImpl(instance()) }

}
