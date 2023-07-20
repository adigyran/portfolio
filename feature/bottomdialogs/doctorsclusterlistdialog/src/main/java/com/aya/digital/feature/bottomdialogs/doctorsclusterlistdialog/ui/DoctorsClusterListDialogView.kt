package com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.ui

import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.ext.argument
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.createFragment
import com.aya.digital.core.ext.toggleVisibility
import com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.di.doctorsClusterListDialogDiModule
import com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.viewmodel.DoctorsClusterListDialogState
import com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.viewmodel.DoctorsClusterListDialogViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiBottomSheetDialogFragment
import com.aya.digital.core.ui.delegates.doctors.doctoritem.ui.DoctorItemDelegate
import com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.databinding.ViewDoctorsClusterListDialogBinding
import com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.ui.model.DoctorsClusterListDialogStateTransformer
import com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.ui.model.DoctorsClusterListDialogUiModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on
import timber.log.Timber

class DoctorsClusterListDialogView :
    DiBottomSheetDialogFragment<ViewDoctorsClusterListDialogBinding, DoctorsClusterListDialogViewModel, DoctorsClusterListDialogState, BaseSideEffect, DoctorsClusterListDialogUiModel, DoctorsClusterListDialogStateTransformer>() {

    private var param: Param by argument("param")

    private var initialised: Boolean = false

    private val viewModelFactory: ((Unit) -> DoctorsClusterListDialogViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> DoctorsClusterListDialogStateTransformer) by kodein.on(
        context = this
    ).factory()

    override fun provideViewModel(): DoctorsClusterListDialogViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): DoctorsClusterListDialogStateTransformer =
        stateTransformerFactory(Unit)


    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            DoctorItemDelegate(viewModel::onDoctorClicked, viewModel::onDoctorFavouriteClicked)
        }
    }

    private lateinit var lm: GridLayoutManager

    override fun provideDiModule(): DI.Module =
        doctorsClusterListDialogDiModule(tryTyGetParentRouter(), param)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
        Timber.d("AAAA")
        binding.btnClose bindClick { viewModel.close() }
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetBehavior.skipCollapsed = true
        with(binding.recycler) {
            itemAnimator = null
            lm = GridLayoutManager(
                context,
                4,
                RecyclerView.VERTICAL,
                false
            )


            layoutManager = lm
            addItemDecoration(DoctorsClusterListDialogDecoration())
        }
    }

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDoctorsClusterListDialogBinding =
        ViewDoctorsClusterListDialogBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: DoctorsClusterListDialogState) {
        stateTransformer(state).run {
            data?.let {
                adapter.items = it
                if (binding.recycler.adapter == null) {
                    binding.recycler.swapAdapter(adapter, true)
                }
            }
        }
    }


    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }

    @Parcelize
    class Param(
        val requestCode: String,
        val doctors:List<DoctorModel>
    ) : Parcelable

    companion object {
        fun getNewInstance(
            requestCode: String,
            doctors:List<DoctorModel>
        ): DoctorsClusterListDialogView =
            createFragment(
                Param(
                    requestCode,
                    doctors
                )
            )
    }
}
