package com.aya.digital.healthapp.patient.navigation.root

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.feature.auth.restorepassword.navigation.RestorePasswordNavigationEvents
import com.aya.digital.core.feature.auth.restorepassword.navigation.RestorePasswordOperationStateParam
import com.aya.digital.core.feature.auth.restorepassword.navigation.RestorePasswordScreen
import com.aya.digital.core.feature.bottomnavhost.navigation.BottomNavHostScreen
import com.aya.digital.core.feature.choosers.multiselect.navigation.SelectWithSearchNavigationEvents
import com.aya.digital.core.feature.choosers.multiselect.navigation.SelectWithSearchScreen
import com.aya.digital.core.feature.doctors.doctorcard.navigation.DoctorCardNavigationEvents
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.navigation.DoctorSearchMapNavigationEvents
import com.aya.digital.core.feature.tabviews.appointments.navigation.AppointmentsNavigationEvents
import com.aya.digital.core.navigation.bottomnavigation.StartScreen
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.graph.coordinator.RootCoordinatorGraph
import com.aya.digital.feature.auth.container.navigation.AuthContainerScreen
import com.aya.digital.feature.bottomdialogs.codedialog.navigation.CodeDialogNavigationEvents
import com.aya.digital.feature.bottomdialogs.codedialog.navigation.CodeDialogScreen
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.navigation.CreateAppointmentDialogNavigationEvents
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.navigation.CreateAppointmentDialogScreen
import com.aya.digital.feature.bottomdialogs.successappointmentdialog.navigation.SuccessAppointmentDialogNavigationEvents
import com.aya.digital.feature.bottomdialogs.successappointmentdialog.navigation.SuccessAppointmentDialogScreen
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.navigation.DateAppointmentsDialogNavigationEvents
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.navigation.DateAppointmentsDialogScreen
import com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.navigation.DoctorsClusterListDialogScreen
import com.aya.digital.feature.rootcontainer.navigation.RootContainerNavigationEvents
import com.github.terrakok.cicerone.Router
import java.lang.ref.WeakReference

class PatientRootCoordinatorGraph(context: Context) : RootCoordinatorGraph {
    private val contextWeak: WeakReference<Context>

    init {
        this.contextWeak = WeakReference(context)
    }

    override fun processEvent(
        event: CoordinatorEvent,
        navigationRouter: Router,
        fragmentManagerWeak: WeakReference<FragmentManager>
    ) {
        fun openRootScreen(startScreen: StartScreen) =
            navigationRouter.newRootScreen(BottomNavHostScreen(startScreen))
        when (event) {
            is RootContainerNavigationEvents.OpenDefaultScreen -> {
                navigationRouter.newRootScreen(AuthContainerScreen)
            }

            is RootContainerNavigationEvents.OpenDefaultScreenAuthorized -> {
                openRootScreen(StartScreen.HOME)
            }

            is RootContainerNavigationEvents.OpenBottomNavigationScreenDefault -> {
                openRootScreen(StartScreen.HOME)
            }

            is RootContainerNavigationEvents.EnterCode -> {
                navigationRouter.navigateTo(
                    CodeDialogScreen("ENTER_CODE", event.requestCode, event.email)
                )
            }

            is RootContainerNavigationEvents.SelectMultipleItems -> {
                navigationRouter.navigateTo(
                    SelectWithSearchScreen(
                        requestCode = event.requestCode,
                        isMultiChoose = true,
                        selectedItems = event.selectedItems
                    )
                )
            }

            is RootContainerNavigationEvents.SelectSingleItem -> {
                navigationRouter.navigateTo(
                    SelectWithSearchScreen(
                        requestCode = event.requestCode,
                        isMultiChoose = false,
                        selectedItems = event.selectedItem?.let { setOf(it) } ?: setOf()
                    )
                )
            }

            is RootContainerNavigationEvents.RestorePassword -> {
                navigationRouter.navigateTo(
                    RestorePasswordScreen(
                        event.requestCode,
                        RestorePasswordOperationStateParam.RestorePassword
                    )
                )
            }

            is RootContainerNavigationEvents.ChangeTempPassword -> {
                navigationRouter.navigateTo(
                    RestorePasswordScreen(
                        event.requestCode,
                        RestorePasswordOperationStateParam.ChangeTempPass
                    )
                )
            }

            is CodeDialogNavigationEvents.FinishWithResult -> {
                navigationRouter.exit()
                navigationRouter.sendResult(event.requestCode, event.result)
            }

            is SelectWithSearchNavigationEvents.FinishWithResult -> {
                navigationRouter.exit()
                navigationRouter.sendResult(event.requestCode, event.result)
            }

            is CodeDialogNavigationEvents.Exit -> {
                navigationRouter.exit()
            }

            is DateAppointmentsDialogNavigationEvents.Exit -> {
                navigationRouter.exit()
            }

            is CreateAppointmentDialogNavigationEvents.FinishWithResult -> {
                navigationRouter.exit()
                navigationRouter.sendResult(event.requestCode, event.result)
            }

            is DateAppointmentsDialogNavigationEvents.FinishWithResult -> {
                navigationRouter.exit()
                navigationRouter.sendResult(event.requestCode, event.result)
            }

            is RestorePasswordNavigationEvents.FinishWithResult -> {
                navigationRouter.exit()
                navigationRouter.sendResult(event.requestCode, event.result)
            }

            is DoctorCardNavigationEvents.CreateAppointment -> {
                navigationRouter.navigateTo(
                    CreateAppointmentDialogScreen(
                        "CREATE_APPOINTMENT",
                        event.requestCode,
                        event.doctorId,
                        event.slotDateTime,
                        event.date
                    )
                )
            }

            is DoctorCardNavigationEvents.OpenSuccessAppointmentCreation -> {
                navigationRouter.navigateTo(
                    SuccessAppointmentDialogScreen(
                        "SUCCESS_APPOINTMENT",
                        event.requestCode,
                        event.appointmentId
                    )
                )
            }

            is AppointmentsNavigationEvents.OpenAppointmentsForSpecificDate -> {
                navigationRouter.navigateTo(
                    DateAppointmentsDialogScreen(
                        "SELECTED_DATE_APPOINTMENTS",
                        event.requestCode,
                        event.date
                    )
                )

            }

            is DoctorSearchMapNavigationEvents.OpenDoctorsCluster -> {
                navigationRouter.navigateTo(
                    DoctorsClusterListDialogScreen(
                        "CLUSTER_LIST",
                        "TTT",
                        event.doctors
                    )
                )
            }

            is CoordinatorEvent.Back -> {
                navigationRouter.exit()
            }
        }
    }
}