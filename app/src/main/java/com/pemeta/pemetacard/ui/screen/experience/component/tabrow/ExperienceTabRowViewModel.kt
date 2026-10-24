package com.pemeta.pemetacard.ui.screen.experience.component.tabrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pemeta.pemetacard.data.repository.PemetaCardRepository
import com.pemeta.pemetacard.model.pengalaman.data.DataJobFinalType

class ExperienceTabRowViewModel(private val repository: PemetaCardRepository) : ViewModel() {

    private val _tabIndex: MutableLiveData<Int> = MutableLiveData(0)

    val tabIndex: LiveData<Int> = _tabIndex

    val tabs = DataJobFinalType.JobFinalTypeList

    fun updateTabIndexBasedOnSwipe(isSwipeToTheLeft: Boolean) {
        _tabIndex.value = when (isSwipeToTheLeft) {
            true -> Math.floorMod(_tabIndex.value!!.plus(1), tabs.size)
            false -> Math.floorMod(_tabIndex.value!!.minus(1), tabs.size)
        }
    }

    fun updateTabIndex(i: Int) {
        _tabIndex.value = i
    }
}