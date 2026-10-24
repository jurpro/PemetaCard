package com.pemeta.pemetacard.ui.screen.client.list.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemeta.pemetacard.data.repository.PemetaCardRepository
import com.pemeta.pemetacard.model.pengalaman.model.CompanyExperience
import com.pemeta.pemetacard.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ExperienceByClientViewModel(private val repository: PemetaCardRepository) : ViewModel() {

    private val _uiStateExperienceByClient: MutableStateFlow<UiState<List<CompanyExperience>>> = MutableStateFlow(UiState.Loading)
    private val _uiStateFinishedExperienceByClient: MutableStateFlow<UiState<List<CompanyExperience>>> = MutableStateFlow(UiState.Loading)
    private val _uiStateOnGoingExperienceByClient: MutableStateFlow<UiState<List<CompanyExperience>>> = MutableStateFlow(UiState.Loading)

    val uiStateExperienceByClient: StateFlow<UiState<List<CompanyExperience>>> get() = _uiStateExperienceByClient
    val uiStateFinishedExperienceByClient: StateFlow<UiState<List<CompanyExperience>>> get() = _uiStateFinishedExperienceByClient
    val uiStateOnGoingExperienceByClient: StateFlow<UiState<List<CompanyExperience>>> get() = _uiStateOnGoingExperienceByClient

    fun getListExperienceByClient(clientId:Int) {
        viewModelScope.launch {
            repository.getListExperienceByClient(clientId)
                .catch {
                    _uiStateExperienceByClient.value = UiState.Error(it.message.toString())
                }
                .collect { listExperienceByClient ->
                    _uiStateExperienceByClient.value = UiState.Success(listExperienceByClient.sortedByDescending { it.periodEnd })
                }
        }
    }

    fun getListFinishedByClient(clientId:Int) {
        viewModelScope.launch {
            repository.getListJobFinished(clientId)
                .catch {
                    _uiStateFinishedExperienceByClient.value = UiState.Error(it.message.toString())
                }
                .collect { listExperienceByClient ->
                    _uiStateFinishedExperienceByClient.value = UiState.Success(listExperienceByClient.sortedByDescending { it.periodEnd })
                }
        }
    }

    fun getListOnGoingByClient(clientId:Int) {
        viewModelScope.launch {
            repository.getListJobOnGoing(clientId)
                .catch {
                    _uiStateOnGoingExperienceByClient.value = UiState.Error(it.message.toString())
                }
                .collect { listExperienceByClient ->
                    _uiStateOnGoingExperienceByClient.value = UiState.Success(listExperienceByClient.sortedByDescending { it.periodEnd })
                }
        }
    }
}