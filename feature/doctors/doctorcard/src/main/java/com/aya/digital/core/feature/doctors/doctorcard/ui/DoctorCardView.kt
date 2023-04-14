package com.aya.digital.core.feature.doctors.doctorcard.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.argument
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.createFragment
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.feature.doctors.doctorcard.databinding.ViewDoctorCardBinding
import com.aya.digital.core.feature.doctors.doctorcard.di.doctorCardDiModule
import com.aya.digital.core.feature.doctors.doctorcard.ui.model.DoctorCardStateTransformer
import com.aya.digital.core.feature.doctors.doctorcard.ui.model.DoctorCardUiModel
import com.aya.digital.core.feature.doctors.doctorcard.viewmodel.DoctorCardState
import com.aya.digital.core.feature.doctors.doctorcard.viewmodel.DoctorCardViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.DoctorDetailsBioDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.DoctorDetailsInsuranceDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.DoctorDetailsTitleDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.ui.DoctorDateTitleDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.ui.DoctorSlotDelegate
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on
import timber.log.Timber

class DoctorCardView :
    DiFragment<ViewDoctorCardBinding, DoctorCardViewModel, DoctorCardState, BaseSideEffect, DoctorCardUiModel, DoctorCardStateTransformer>() {

    private var param: Param by argument("param")

    private val viewModelFactory: ((Unit) -> DoctorCardViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> DoctorCardStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate {
                DoctorDetailsTitleDelegate()
            }
            delegate {
                DoctorDetailsBioDelegate()
            }
            delegate { DoctorSlotDelegate(viewModel::onSlotClicked) }
            delegate { DoctorDateTitleDelegate() }
            delegate { DoctorDetailsInsuranceDelegate() }
        }
    }

    private lateinit var lm: GridLayoutManager

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
        binding.bookBtn bindClick { viewModel.onBookClicked() }
        binding.detailsBtn bindClick { viewModel.onDetailsClicked() }
        with(binding.recycler) {
            itemAnimator = null
            setHasFixedSize(true)
            setItemViewCacheSize(30)
            isNestedScrollingEnabled = false


            lm = GridLayoutManager(
                context,
                4,
                RecyclerView.VERTICAL,
                false
            )
            //lm.spanSizeLookup = DoctorCardSpanSizeLookup(binding.recycler)

            layoutManager = lm
            addItemDecoration(DoctorCardDecoration())
        }
    }

    override fun provideDiModule(): DI.Module = doctorCardDiModule(tryTyGetParentRouter(), param)

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDoctorCardBinding =
        ViewDoctorCardBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: DoctorCardState) {
        stateTransformer(state).run {
            doctorName?.let {
                binding.toolbar.nameTv.text = it
            }
            doctorSpeciality?.let {
                binding.toolbar.title.text = it
            }
            doctorClinic?.let {
                binding.toolbar.clinicTv.text = it
            }
            doctorAvatar?.let {
                Glide
                    .with(binding.toolbar.avatar)
                    .load(it)
                    .transform(
                        CircleCrop()
                    )
                    .dontAnimate()
                    .into(binding.toolbar.avatar)
            }
            data?.let {
                adapter.items = it
                if (binding.recycler.adapter == null) {
                    binding.recycler.swapAdapter(adapter, true)
                    lm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
                        override fun getSpanSize(position: Int): Int {
                            val findViewHolderForAdapterPosition =
                                binding.recycler.findViewHolderForAdapterPosition(position)
                            Timber.d(findViewHolderForAdapterPosition.toString())
                            return 4
                            /*val findViewByPosition = lm.findViewByPosition(position) ?: return 4
                            val findContainingViewHolder =
                                binding.recycler.findContainingViewHolder(findViewByPosition)
                            Timber.d(findContainingViewHolder.toString())
                            return findContainingViewHolder?.let { viewHolder ->
                                when(viewHolder)
                                {
                                    is DoctorDetailsTitleDelegate.ViewHolder -> 4
                                    is DoctorDetailsBioDelegate.ViewHolder -> 4
                                    is DoctorDetailsInsuranceDelegate.ViewHolder -> 4
                                    is DoctorDateTitleDelegate.ViewHolder -> 4
                                    is DoctorSlotDelegate.ViewHolder -> 1
                                    else -> -1
                                }
                            }?: 4*/
                        }

                    }
                }
            }
        }
    }

    @Parcelize
    class Param(
        val doctorId: Int
    ) : Parcelable

    override fun provideViewModel(): DoctorCardViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): DoctorCardStateTransformer =
        stateTransformerFactory(Unit)

    companion object {
        fun getNewInstance(doctorId: Int): DoctorCardView =
            createFragment(
                Param(doctorId)
            )
    }
}
