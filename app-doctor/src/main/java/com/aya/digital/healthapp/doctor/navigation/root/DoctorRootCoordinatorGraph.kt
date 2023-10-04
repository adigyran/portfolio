package com.aya.digital.healthapp.doctor.navigation.root

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.feature.bottomnavhost.navigation.BottomNavHostScreen
import com.aya.digital.core.feature.choosers.selectwithsearch.navigation.SelectWithSearchNavigationEvents
import com.aya.digital.core.feature.choosers.selectwithsearch.navigation.SelectWithSearchScreen
import com.aya.digital.core.feature.tabviews.appointmentsscheduler.navigation.AppointmentsSchedulerNavigationEvents
import com.aya.digital.core.feature.videocall.videocallscreen.navigation.VideoCallScreenNavigationEvents
import com.aya.digital.core.feature.videocall.videocallscreen.navigation.VideoCallScreenScreen
import com.aya.digital.core.feature.videocall.videocallservice.VideoService
import com.aya.digital.core.navigation.bottomnavigation.StartScreen
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.graph.coordinator.RootCoordinatorGraph
import com.aya.digital.feature.auth.container.navigation.AuthContainerScreen
import com.aya.digital.feature.bottomdialogs.createscheduledialog.navigation.CreateScheduleDialogNavigationEvents
import com.aya.digital.feature.bottomdialogs.createscheduledialog.navigation.CreateScheduleDialogScreen
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.navigation.DateAppointmentsDialogNavigationEvents
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.navigation.DateAppointmentsDialogScreen
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.ui.DateAppointmentsDialogView
import com.aya.digital.feature.rootcontainer.navigation.RootContainerNavigationEvents
import com.github.terrakok.cicerone.Router
import java.lang.ref.WeakReference

class DoctorRootCoordinatorGraph(context: Context) : RootCoordinatorGraph {
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
                openRootScreen(StartScreen.APPOINTMENTS)
            }

            is RootContainerNavigationEvents.OpenBottomNavigationScreenDefault -> {
                openRootScreen(StartScreen.APPOINTMENTS)
            }

            is AppointmentsSchedulerNavigationEvents.OpenAppointmentsForSpecificSlot -> {
                navigationRouter.navigateTo(
                    DateAppointmentsDialogScreen(
                        tag = "SELECTED_SLOt_APPOINTMENTS",
                        requestCode = event.requestCode,
                        dialogParam = DateAppointmentsDialogView.DialogParam.IntervalParam(
                            startDateTime = event.startDateTime,
                            endDateTime = event.endDateTime
                        )
                    )
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

            is AppointmentsSchedulerNavigationEvents.CreateSlots -> {
                navigationRouter.navigateTo(
                    CreateScheduleDialogScreen(
                        tag = "CREATE_SCHEDULE",
                        requestCode = event.requestCode,
                        date = event.date
                    )
                )
            }

            is RootContainerNavigationEvents.OpenVideoCall -> {
                navigationRouter.navigateTo(VideoCallScreenScreen(event.roomId))
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

            is DateAppointmentsDialogNavigationEvents.FinishWithResult -> {
                navigationRouter.exit()
                navigationRouter.sendResult(event.requestCode, event.result)
            }

            is SelectWithSearchNavigationEvents.FinishWithResult -> {
                navigationRouter.exit()
                navigationRouter.sendResult(event.requestCode, event.result)
            }

            is CreateScheduleDialogNavigationEvents.FinishWithResult -> {
                navigationRouter.exit()
                navigationRouter.sendResult(event.requestCode, event.result)
            }

            is CreateScheduleDialogNavigationEvents.Exit -> {
                navigationRouter.exit()
            }

            is CoordinatorEvent.Back -> {
                navigationRouter.exit()
            }

        }
    }
}