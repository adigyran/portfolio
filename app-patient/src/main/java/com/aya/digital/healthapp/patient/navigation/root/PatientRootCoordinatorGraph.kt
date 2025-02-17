package com.aya.digital.healthapp.patient.navigation.root

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.feature.auth.restorepassword.navigation.RestorePasswordNavigationEvents
import com.aya.digital.core.feature.auth.restorepassword.navigation.RestorePasswordOperationStateParam
import com.aya.digital.core.feature.auth.restorepassword.navigation.RestorePasswordScreen
import com.aya.digital.core.feature.bottomnavhost.navigation.BottomNavHostNavigationEvents
import com.aya.digital.core.feature.bottomnavhost.navigation.BottomNavHostScreen
import com.aya.digital.core.feature.choosers.selectwithsearch.navigation.SelectWithSearchNavigationEvents
import com.aya.digital.core.feature.choosers.selectwithsearch.navigation.SelectWithSearchScreen
import com.aya.digital.core.feature.doctors.doctorcard.navigation.DoctorCardNavigationEvents
import com.aya.digital.core.feature.doctors.doctorcard.navigation.DoctorCardScreen
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.navigation.DoctorSearchMapNavigationEvents
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.navigation.DoctorSearchMapScreen
import com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.navigation.DoctorSearchListNavigationEvents
import com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.navigation.DoctorSearchListScreen
import com.aya.digital.core.feature.tabviews.appointments.navigation.AppointmentsNavigationEvents
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.navigation.DoctorSearchContainerNavigationEvents
import com.aya.digital.core.feature.videocall.videocallscreen.navigation.VideoCallScreenNavigationEvents
import com.aya.digital.core.feature.videocall.videocallscreen.navigation.VideoCallScreenScreen
import com.aya.digital.core.feature.videocall.videocallservice.VideoService
import com.aya.digital.core.navigation.bottomnavigation.StartScreen
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.graph.coordinator.RootCoordinatorGraph
import com.aya.digital.feature.auth.container.navigation.AuthContainerScreen
import com.aya.digital.feature.bottomdialogs.codedialog.navigation.CodeDialogNavigationEvents
import com.aya.digital.feature.bottomdialogs.codedialog.navigation.CodeDialogScreen
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.navigation.CreateAppointmentDialogNavigationEvents
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.navigation.CreateAppointmentDialogScreen
import com.aya.digital.feature.bottomdialogs.successappointmentdialog.navigation.SuccessAppointmentDialogScreen
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.navigation.DateAppointmentsDialogNavigationEvents
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.navigation.DateAppointmentsDialogScreen
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.ui.DateAppointmentsDialogView
import com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.navigation.DoctorsClusterListDialogNavigationEvents
import com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.navigation.DoctorsClusterListDialogScreen
import com.aya.digital.feature.rootcontainer.navigation.RootContainerNavigationEvents
import com.aya.digital.feature.videocallcontainer.navigation.VideoContainerScreen
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
                    CodeDialogScreen("ENTER_CODE", event.requestCode, event.value)
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

            is RootContainerNavigationEvents.OpenVideoCall -> {
               // navigationRouter.navigateTo(VideoCallScreenScreen(event.roomId))
                navigationRouter.navigateTo(VideoContainerScreen(event.roomId))
            }
            VideoCallScreenNavigationEvents.Back -> {
                navigationRouter.exit()
            }

            is VideoCallScreenNavigationEvents.StartForegroundService -> {
                contextWeak.get()?.let {
                    VideoService.startService(it,event.roomName)
                }

            }

            is VideoCallScreenNavigationEvents.StopForegroundService -> {
                contextWeak.get()?.let {
                    VideoService.stopService(it)
                }
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
                navigationRouter.sendResult(event.requestCode, event.result)
                navigationRouter.exit()

            }

            is SelectWithSearchNavigationEvents.FinishWithResult -> {
                navigationRouter.sendResult(event.requestCode, event.result)
                navigationRouter.exit()
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

            is CreateAppointmentDialogNavigationEvents.Exit -> {
                navigationRouter.exit()
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
                        tag = "SUCCESS_APPOINTMENT",
                        requestCode = event.requestCode,
                        appointmentId = event.appointmentId
                    )
                )
            }

            is AppointmentsNavigationEvents.OpenAppointmentsForSpecificDate -> {
                navigationRouter.navigateTo(
                    DateAppointmentsDialogScreen(
                        tag = "SELECTED_DATE_APPOINTMENTS",
                        requestCode = event.requestCode,
                        dialogParam = DateAppointmentsDialogView.DialogParam.DateParam(date = event.date)
                    )
                )

            }

            is DoctorSearchMapNavigationEvents.OpenDoctorsCluster -> {
                navigationRouter.navigateTo(
                    DoctorsClusterListDialogScreen(
                        "CLUSTER_LIST",
                        event.requestCode,
                        event.doctors
                    )
                )
            }


           /* is DoctorsClusterListDialogNavigationEvents.OpenDoctorCard -> {
                navigationRouter.navigateTo(DoctorCardScreen(event.doctorId))
            }*/


            is DoctorsClusterListDialogNavigationEvents.FinishWithResult -> {
                navigationRouter.sendResult(event.requestCode, event.result)
                navigationRouter.exit()
            }
            is BottomNavHostNavigationEvents.Finish -> navigationRouter.exit()

            is CoordinatorEvent.Back -> {
                navigationRouter.exit()
            }

            DoctorSearchContainerNavigationEvents.OpenDefaultScreen -> {
                navigationRouter.replaceScreen(DoctorSearchListScreen)
            }

            DoctorSearchContainerNavigationEvents.OpenMap -> {
                navigationRouter.replaceScreen(DoctorSearchMapScreen)
            }

            DoctorSearchContainerNavigationEvents.OpenList -> {
                navigationRouter.replaceScreen(DoctorSearchListScreen)
            }
            is DoctorSearchMapNavigationEvents.OpenDoctorCard -> {
                navigationRouter.navigateTo(DoctorCardScreen(event.doctorId))
            }

            is DoctorSearchListNavigationEvents.OpenDoctorCard -> {
                navigationRouter.navigateTo(DoctorCardScreen(event.doctorId))
            }
        }
    }
}