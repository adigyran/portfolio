package com.aya.digital.core.domain.profile.di
import com.aya.digital.core.domain.profile.emergencycontact.GetEmergencyContactUseCase
import com.aya.digital.core.domain.profile.emergencycontact.impl.GetEmergencyContactUseCaseImpl
import com.aya.digital.core.domain.profile.emergencycontact.SaveEmergencyContactUseCase
import com.aya.digital.core.domain.profile.emergencycontact.impl.SaveEmergencyContactUseCaseImpl
import com.aya.digital.core.domain.profile.generalinfo.edit.SaveProfileInfoUseCase
import com.aya.digital.core.domain.profile.generalinfo.edit.impl.SaveProfileInfoUseCaseImpl
import com.aya.digital.core.domain.profile.generalinfo.edit.SetAvatarUseCase
import com.aya.digital.core.domain.profile.generalinfo.edit.impl.SetAvatarUseCaseImpl
import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileBriefUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileInfoUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.impl.GetProfileInfoUseCaseImpl
import com.aya.digital.core.domain.profile.generalinfo.view.impl.GetProfileUseCaseImpl
import com.aya.digital.core.domain.profile.insurance.AddInsuranceUseCase
import com.aya.digital.core.domain.profile.insurance.DeleteInsuranceUseCase
import com.aya.digital.core.domain.profile.insurance.GetInsuransesUseCase
import com.aya.digital.core.domain.profile.insurance.impl.AddInsuranceUseCaseImpl
import com.aya.digital.core.domain.profile.insurance.impl.DeleteInsuranceUseCaseImpl
import com.aya.digital.core.domain.profile.insurance.impl.GetInsuransesUseCaseImpl
import com.aya.digital.core.domain.profile.notifications.GetEmailNotificationsStatusUseCase
import com.aya.digital.core.domain.profile.notifications.impl.GetEmailNotificationsStatusUseCaseImpl
import com.aya.digital.core.domain.profile.notifications.SetEmailNotificationsStatusUseCase
import com.aya.digital.core.domain.profile.notifications.impl.SetEmailNotificationsStatusUseCaseImpl
import com.aya.digital.core.domain.profile.security.changeemail.ChangeEmailGetCodeUseCase
import com.aya.digital.core.domain.profile.security.changeemail.ChangeEmailUseCase
import com.aya.digital.core.domain.profile.security.changeemail.impl.ChangeEmailGetCodeUseCaseImpl
import com.aya.digital.core.domain.profile.security.changeemail.impl.ChangeEmailUseCaseImpl
import com.aya.digital.core.domain.profile.security.changepassword.ChangePasswordUseCase
import com.aya.digital.core.domain.profile.security.changepassword.impl.ChangePasswordUseCaseImpl
import com.aya.digital.core.domain.profile.security.summary.GetSecuritySummaryUseCase
import com.aya.digital.core.domain.profile.security.summary.impl.GetSecuritySummaryUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun profileDomainDiModule() = DI.Module("profileDomainDiModule") {
    //profile
    //get profile general info
    bind<GetProfileBriefUseCase>() with singleton { GetProfileUseCaseImpl(instance()) }
    bind<GetProfileInfoUseCase>() with singleton { GetProfileInfoUseCaseImpl(instance()) }
    //save profile
    bind<SaveProfileInfoUseCase>() with singleton { SaveProfileInfoUseCaseImpl(instance(),instance()) }
    //set avatar
    bind<SetAvatarUseCase>() with singleton { SetAvatarUseCaseImpl() }

    //emergencyContact
    bind<GetEmergencyContactUseCase>() with singleton { GetEmergencyContactUseCaseImpl() }
    bind<SaveEmergencyContactUseCase>() with singleton { SaveEmergencyContactUseCaseImpl() }

    //insurance
    bind<GetInsuransesUseCase>() with singleton { GetInsuransesUseCaseImpl() }
    bind<AddInsuranceUseCase>() with singleton { AddInsuranceUseCaseImpl() }
    bind<DeleteInsuranceUseCase>() with singleton { DeleteInsuranceUseCaseImpl() }

    //notifications
    bind<GetEmailNotificationsStatusUseCase>() with singleton { GetEmailNotificationsStatusUseCaseImpl() }
    bind<SetEmailNotificationsStatusUseCase>() with singleton { SetEmailNotificationsStatusUseCaseImpl() }

    //security
    //summary
    bind<GetSecuritySummaryUseCase>() with singleton { GetSecuritySummaryUseCaseImpl() }
    //change email
    bind<ChangeEmailUseCase>() with singleton { ChangeEmailUseCaseImpl(instance()) }
    bind<ChangeEmailGetCodeUseCase>() with singleton { ChangeEmailGetCodeUseCaseImpl(instance(),instance()) }
    //change password
    bind<ChangePasswordUseCase>() with singleton { ChangePasswordUseCaseImpl(instance(),instance()) }

}