package com.aya.digital.healthapp.patient.navigation.videocall

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
import com.aya.digital.feature.rootcontainer.navigation.RootContainerScreen
import com.aya.digital.feature.videocallcontainer.navigation.VideoContainerNavigationEvents
import com.aya.digital.feature.videocallcontainer.navigation.VideoContainerScreen
import com.github.terrakok.cicerone.Router
import java.lang.ref.WeakReference

class VideoCallContainerCoordinatorGraph(context: Context) : RootCoordinatorGraph {
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
            is VideoContainerNavigationEvents.OpenVideoCall -> {
                navigationRouter.navigateTo(VideoCallScreenScreen(event.roomId))
            }
            is VideoCallScreenNavigationEvents.Back ->{
                navigationRouter.exit()
                navigationRouter.navigateTo(RootContainerScreen)
            }

            is CoordinatorEvent.Back -> {
                navigationRouter.exit()
                navigationRouter.navigateTo(RootContainerScreen)
            }
        }
    }
}