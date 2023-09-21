package com.aya.digital.core.domain.profile.di

import com.aya.digital.core.domain.profile.attachment.GetAttachmentByIdUseCase
import com.aya.digital.core.domain.profile.attachment.impl.GetAttachmentByIdUseCaseImpl
import com.aya.digital.core.domain.profile.emergencycontact.GetEmergencyContactUseCase
import com.aya.digital.core.domain.profile.emergencycontact.impl.GetEmergencyContactUseCaseImpl
import com.aya.digital.core.domain.profile.emergencycontact.SaveEmergencyContactUseCase
import com.aya.digital.core.domain.profile.emergencycontact.impl.SaveEmergencyContactUseCaseImpl
import com.aya.digital.core.domain.profile.generalinfo.edit.SaveProfileInfoUseCase
import com.aya.digital.core.domain.profile.generalinfo.edit.impl.SaveDoctorProfileInfoUseCaseImpl
import com.aya.digital.core.domain.profile.generalinfo.edit.SetAvatarUseCase
import com.aya.digital.core.domain.profile.generalinfo.edit.UpdateDoctorBioUseCase
import com.aya.digital.core.domain.profile.generalinfo.edit.UpdateDoctorLanguagesUseCase
import com.aya.digital.core.domain.profile.generalinfo.edit.UpdateDoctorMedicalDegreesUseCase
import com.aya.digital.core.domain.profile.generalinfo.edit.UpdateDoctorSpecialitiesUseCase
import com.aya.digital.core.domain.profile.generalinfo.edit.impl.SavePatientProfileInfoUseCaseImpl
import com.aya.digital.core.domain.profile.generalinfo.edit.impl.SetAvatarUseCaseImpl
import com.aya.digital.core.domain.profile.generalinfo.edit.impl.UpdateDoctorBioUseCaseImpl
import com.aya.digital.core.domain.profile.generalinfo.edit.impl.UpdateDoctorLanguagesUseCaseImpl
import com.aya.digital.core.domain.profile.generalinfo.edit.impl.UpdateDoctorMedicalDegreesUseCaseImpl
import com.aya.digital.core.domain.profile.generalinfo.edit.impl.UpdateDoctorSpecialitiesUseCaseImpl
import com.aya.digital.core.domain.profile.generalinfo.view.GetDoctorBioUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.GetDoctorLanguagesUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.GetDoctorMedicalDegreesUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileAvatarUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileBriefUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileInfoUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.impl.GetDoctorBioUseCaseImpl
import com.aya.digital.core.domain.profile.generalinfo.view.impl.GetDoctorLanguagesUseCaseImpl
import com.aya.digital.core.domain.profile.generalinfo.view.impl.GetDoctorMedicalDegreesUseCaseImpl
import com.aya.digital.core.domain.profile.generalinfo.view.impl.GetDoctorProfileInfoUseCaseImpl
import com.aya.digital.core.domain.profile.generalinfo.view.impl.GetDoctorSpecialitiesUseCaseImpl
import com.aya.digital.core.domain.profile.generalinfo.view.impl.GetProfileAvatarUseCaseImpl
import com.aya.digital.core.domain.profile.generalinfo.view.impl.GetPatientProfileInfoUseCaseImpl
import com.aya.digital.core.domain.profile.generalinfo.view.impl.GetProfileUseCaseImpl
import com.aya.digital.core.domain.profile.insurance.*
import com.aya.digital.core.domain.profile.insurance.impl.*
import com.aya.digital.core.domain.profile.insurance.impl.AddInsuranceUseCaseImpl
import com.aya.digital.core.domain.profile.insurance.impl.DeleteInsuranceUseCaseImpl
import com.aya.digital.core.domain.profile.insurance.impl.GetInsurancesUseCaseImpl
import com.aya.digital.core.domain.profile.notifications.GetNotificationsStatusUseCase
import com.aya.digital.core.domain.profile.notifications.impl.GetNotificationsStatusUseCaseImpl
import com.aya.digital.core.domain.profile.notifications.SetNotificationsStatusUseCase
import com.aya.digital.core.domain.profile.notifications.impl.SetNotificationsStatusUseCaseImpl
import com.aya.digital.core.domain.profile.security.changeemail.ChangeEmailGetCodeUseCase
import com.aya.digital.core.domain.profile.security.changeemail.ChangeEmailUseCase
import com.aya.digital.core.domain.profile.security.changeemail.impl.ChangeEmailGetCodeUseCaseImpl
import com.aya.digital.core.domain.profile.security.changeemail.impl.ChangeEmailUseCaseImpl
import com.aya.digital.core.domain.profile.security.changepassword.ChangePasswordUseCase
import com.aya.digital.core.domain.profile.security.changepassword.impl.ChangePasswordUseCaseImpl
import com.aya.digital.core.domain.profile.security.changephone.ChangePhoneConfirmCodeUseCase
import com.aya.digital.core.domain.profile.security.changephone.ChangePhoneGetCodeUseCase
import com.aya.digital.core.domain.profile.security.changephone.ChangePhoneUseCase
import com.aya.digital.core.domain.profile.security.changephone.GetPhoneVerifiedStatusUseCase
import com.aya.digital.core.domain.profile.security.changephone.impl.ChangePhoneConfirmCodeUseCaseImpl
import com.aya.digital.core.domain.profile.security.changephone.impl.ChangePhoneGetCodeUseCaseImpl
import com.aya.digital.core.domain.profile.security.changephone.impl.ChangePhoneUseCaseImpl
import com.aya.digital.core.domain.profile.security.changephone.impl.GetPhoneVerifiedStatusUseCaseImpl
import com.aya.digital.core.domain.profile.security.logout.LogoutUseCase
import com.aya.digital.core.domain.profile.security.logout.impl.LogoutUseCaseImpl
import com.aya.digital.core.domain.profile.security.summary.GetSecuritySummaryUseCase
import com.aya.digital.core.domain.profile.security.summary.impl.GetSecuritySummaryUseCaseImpl
import com.aya.digital.core.navigation.AppFlavour
import com.aya.digital.core.navigation.Flavor
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun profileDomainDiModule() = DI.Module("profileDomainDiModule") {
    //profile
    //get profile general info
    bind<GetProfileBriefUseCase>() with singleton { GetProfileUseCaseImpl(instance(), instance()) }
    bind<GetProfileInfoUseCase>() with singleton {
        val flavour = instance<AppFlavour>()
        when (flavour.flavour) {
            is Flavor.Patient -> GetPatientProfileInfoUseCaseImpl(
                profileRepository = instance(),
                getProfileBriefUseCase = instance(),
                progressRepository = instance()
            )

            is Flavor.Doctor -> GetDoctorProfileInfoUseCaseImpl(
                getDoctorBioUseCase = instance(),
                getDoctorLanguagesUseCase = instance(),
                getDoctorMedicalDegreesUseCase = instance(),
                getDoctorSpecialitiesUseCase = instance(),
                getProfileBriefUseCase = instance(),
                progressRepository = instance()
            )

            else -> GetPatientProfileInfoUseCaseImpl(
                profileRepository = instance(),
                getProfileBriefUseCase = instance(),
                progressRepository = instance()
            )
        }
    }
    //save profile
    bind<SaveProfileInfoUseCase>() with singleton {
        val flavour = instance<AppFlavour>()
        when (flavour.flavour) {
            is Flavor.Patient -> SavePatientProfileInfoUseCaseImpl(
                profileRepository = instance(),
                dateTimeUtils = instance(),
                progressRepository = instance()
            )

            is Flavor.Doctor -> SaveDoctorProfileInfoUseCaseImpl(
                profileRepository = instance(),
                dateTimeUtils = instance(),
                progressRepository = instance(),
                updateDoctorBioUseCase = instance(),
                updateDoctorLanguagesUseCase = instance(),
                updateDoctorMedicalDegreesUseCase = instance(),
                updateDoctorSpecialitiesUseCase = instance()
            )

            else -> SavePatientProfileInfoUseCaseImpl(
                profileRepository = instance(),
                dateTimeUtils = instance(),
                progressRepository = instance()
            )
        }
    }
    //set avatar
    bind<SetAvatarUseCase>() with singleton { SetAvatarUseCaseImpl(instance(), instance()) }
    //get avatar
    bind<GetProfileAvatarUseCase>() with singleton {
        GetProfileAvatarUseCaseImpl(
            instance(),
            instance()
        )
    }

    bind<GetDoctorBioUseCase>() with singleton {
        GetDoctorBioUseCaseImpl(
            instance(),
            instance()
        )
    }

    bind<GetDoctorLanguagesUseCase>() with singleton {
        GetDoctorLanguagesUseCaseImpl(
            instance(),
            instance()
        )
    }

    bind<GetDoctorMedicalDegreesUseCase>() with singleton {
        GetDoctorMedicalDegreesUseCaseImpl(
            instance(),
            instance()
        )
    }

    bind<GetDoctorSpecialitiesUseCaseImpl>() with singleton {
        GetDoctorSpecialitiesUseCaseImpl(
            instance(),
            instance()
        )
    }


    //phone
    bind<ChangePhoneGetCodeUseCase>() with singleton {
        ChangePhoneGetCodeUseCaseImpl(
            instance(),
            instance()
        )
    }

    bind<ChangePhoneConfirmCodeUseCase>() with singleton {
        ChangePhoneConfirmCodeUseCaseImpl(
            instance(),
            instance()
        )
    }

    bind<GetPhoneVerifiedStatusUseCase>() with singleton {
        GetPhoneVerifiedStatusUseCaseImpl(
            instance(),
            instance()
        )
    }

    bind<ChangePhoneUseCase>() with singleton {
        ChangePhoneUseCaseImpl(
            instance(),
            instance()
        )
    }

    //emergencyContact
    bind<GetEmergencyContactUseCase>() with singleton {
        GetEmergencyContactUseCaseImpl(
            instance(),
            instance()
        )
    }

    bind<SaveEmergencyContactUseCase>() with singleton {
        SaveEmergencyContactUseCaseImpl(
            instance(),
            instance()
        )
    }

    //insurance
    bind<GetInsurancesUseCase>() with singleton {
        GetInsurancesUseCaseImpl(
            instance(),
            instance(),
            instance()
        )
    }
    bind<GetInsuranceByIdUseCase>() with singleton {
        GetInsuranceByIdUseCaseImpl(
            instance(),
            instance(),
            instance()
        )
    }

    bind<GetDoctorInsurancesUseCase>() with singleton {
        GetDoctorInsurancesUseCaseImpl(
            instance(),
            instance()
        )
    }

    bind<UpdateDoctorInsurancesUseCase>() with singleton {
        UpdateDoctorInsurancesUseCaseImpl(
            instance(),
            instance()
        )
    }

    bind<UpdateDoctorLanguagesUseCase>() with singleton {
        UpdateDoctorLanguagesUseCaseImpl(
            instance(),
            instance()
        )
    }
    bind<UpdateDoctorSpecialitiesUseCase>() with singleton {
        UpdateDoctorSpecialitiesUseCaseImpl(
            instance(),
            instance()
        )
    }
    bind<UpdateDoctorMedicalDegreesUseCase>() with singleton {
        UpdateDoctorMedicalDegreesUseCaseImpl(
            instance(),
            instance()
        )
    }

    bind<UpdateDoctorBioUseCase>() with singleton {
        UpdateDoctorBioUseCaseImpl(
            instance(),
            instance()
        )
    }
    bind<AddInsuranceUseCase>() with singleton { AddInsuranceUseCaseImpl(instance(), instance()) }
    bind<SaveInsuranceUseCase>() with singleton { SaveInsuranceUseCaseImpl(instance(), instance()) }
    bind<DeleteInsuranceUseCase>() with singleton {
        DeleteInsuranceUseCaseImpl(
            instance(),
            instance()
        )
    }
    bind<UploadInsuranceAttachmentUseCase>() with singleton {
        UploadInsuranceAttachmentUseCaseImpl(
            instance(),
            instance()
        )
    }
    bind<GetInsuranceAttachmentByIdUseCase>() with singleton {
        GetInsuranceAttachmentByIdUseCaseImpl(
            instance(),
            instance()
        )
    }


    //notifications
    bind<GetNotificationsStatusUseCase>() with singleton {
        GetNotificationsStatusUseCaseImpl(
            instance(),
            instance()
        )
    }
    bind<SetNotificationsStatusUseCase>() with singleton {
        SetNotificationsStatusUseCaseImpl(
            instance(),
            instance()
        )
    }

    //security
    //summary
    bind<GetSecuritySummaryUseCase>() with singleton {
        GetSecuritySummaryUseCaseImpl(
            instance(),
            instance()
        )
    }
    //change email
    bind<ChangeEmailUseCase>() with singleton { ChangeEmailUseCaseImpl(instance()) }
    bind<ChangeEmailGetCodeUseCase>() with singleton {
        ChangeEmailGetCodeUseCaseImpl(
            instance(),
            instance(),
            instance()
        )
    }
    //change password
    bind<ChangePasswordUseCase>() with singleton {
        ChangePasswordUseCaseImpl(
            instance(),
            instance(),
            instance()
        )
    }
    //logout
    bind<LogoutUseCase>() with singleton { LogoutUseCaseImpl(instance()) }

    bind<GetAttachmentByIdUseCase>() with singleton {
        GetAttachmentByIdUseCaseImpl(
            instance(),
            instance()
        )
    }


}