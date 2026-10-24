package com.pemeta.pemetacard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pemeta.pemetacard.data.repository.PemetaCardRepository
import com.pemeta.pemetacard.ui.screen.client.list.component.ExperienceByClientViewModel
import com.pemeta.pemetacard.ui.screen.client.list.component.ListClientViewModel
import com.pemeta.pemetacard.ui.screen.experience.component.tabrow.ExperienceTabRowViewModel
import com.pemeta.pemetacard.ui.screen.experience.list.ListViewModel
import com.pemeta.pemetacard.ui.screen.experience.ongoing.component.OnGoingViewModel
import com.pemeta.pemetacard.ui.screen.experience.service.ListServiceViewModel
import com.pemeta.pemetacard.ui.screen.experience.typeJob.component.ExperienceByTypeViewModel
import com.pemeta.pemetacard.ui.screen.info.list.ListNewsInfoViewModel
import com.pemeta.pemetacard.ui.screen.legal.list.component.ListLegalitasViewModel
import com.pemeta.pemetacard.ui.screen.team.component.TeamViewModel

class ViewModelFactory(
    private val repository: PemetaCardRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ListClientViewModel::class.java)) {
            return ListClientViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(OnGoingViewModel::class.java)) {
            return OnGoingViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ExperienceByClientViewModel::class.java)) {
            return ExperienceByClientViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ExperienceTabRowViewModel::class.java)) {
            return ExperienceTabRowViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ListLegalitasViewModel::class.java)) {
            return ListLegalitasViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ListNewsInfoViewModel::class.java)) {
            return ListNewsInfoViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ExperienceByTypeViewModel::class.java)) {
            return ExperienceByTypeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(TeamViewModel::class.java)) {
            return TeamViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ListServiceViewModel::class.java)) {
            return ListServiceViewModel(repository) as T
        }

        /* else if (modelClass.isAssignableFrom(DetailRewardViewModel::class.java)) {
            return DetailRewardViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        } */
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}