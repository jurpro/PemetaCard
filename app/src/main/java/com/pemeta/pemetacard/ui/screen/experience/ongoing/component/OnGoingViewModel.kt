package com.pemeta.pemetacard.ui.screen.experience.ongoing.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemeta.pemetacard.data.repository.PemetaCardRepository
import com.pemeta.pemetacard.model.pengalaman.model.CompanyExperience
import com.pemeta.pemetacard.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class OnGoingViewModel(private val repository: PemetaCardRepository) : ViewModel() {
    private val _uiStateOnGoing: MutableStateFlow<UiState<List<CompanyExperience>>> = MutableStateFlow(UiState.Loading)

    val uiStateOnGoing: StateFlow<UiState<List<CompanyExperience>>> get() = _uiStateOnGoing

    fun getListOnGoingProject() {
        viewModelScope.launch {
            repository.getListOnGoing()
                .catch {
                    _uiStateOnGoing.value = UiState.Error(it.message.toString())
                }
                .collect { listOnGoingProject ->
                    _uiStateOnGoing.value = UiState.Success(listOnGoingProject.sortedByDescending { it.periodEnd })
                }
        }
    }
}