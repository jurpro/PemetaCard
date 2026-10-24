package com.pemeta.pemetacard.ui.screen.experience.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemeta.pemetacard.data.repository.PemetaCardRepository
import com.pemeta.pemetacard.model.UiState
import com.pemeta.pemetacard.model.pengalaman.model.CompanyExperience
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ListServiceViewModel(private val repository: PemetaCardRepository) : ViewModel() {

    private val _uiStateExperienceByService: MutableStateFlow<UiState<List<CompanyExperience>>> = MutableStateFlow(UiState.Loading)
    private val _uiStateFinishedExperienceByService: MutableStateFlow<UiState<List<CompanyExperience>>> = MutableStateFlow(UiState.Loading)
    private val _uiStateOnGoingExperienceByService: MutableStateFlow<UiState<List<CompanyExperience>>> = MutableStateFlow(UiState.Loading)

    val uiStateExperienceByService: StateFlow<UiState<List<CompanyExperience>>> get() = _uiStateExperienceByService
    val uiStateFinishedExperienceByService: StateFlow<UiState<List<CompanyExperience>>> get() = _uiStateFinishedExperienceByService
    val uiStateOnGoingExperienceByService: StateFlow<UiState<List<CompanyExperience>>> get() = _uiStateOnGoingExperienceByService

    fun getListExperienceByBudget(serviceId:Int) {
        viewModelScope.launch {
            repository.getListExperienceByBudget(serviceId)
                .catch {
                    _uiStateExperienceByService.value = UiState.Error(it.message.toString())
                }
                .collect { listExperienceByService ->
                    _uiStateExperienceByService.value = UiState.Success(listExperienceByService.sortedByDescending { it.periodEnd })
                }
        }
    }

    fun getListFinishedByBudget(serviceId:Int) {
        viewModelScope.launch {
            repository.getListFinishedByBudget(serviceId)
                .catch {
                    _uiStateFinishedExperienceByService.value = UiState.Error(it.message.toString())
                }
                .collect { listExperienceByService ->
                    _uiStateFinishedExperienceByService.value = UiState.Success(listExperienceByService.sortedByDescending { it.periodEnd })
                }
        }
    }

    fun getListOnGoingByBudget(serviceId:Int) {
        viewModelScope.launch {
            repository.getListOnGoingByBudget(serviceId)
                .catch {
                    _uiStateOnGoingExperienceByService.value = UiState.Error(it.message.toString())
                }
                .collect { listExperienceByService ->
                    _uiStateOnGoingExperienceByService.value = UiState.Success(listExperienceByService.sortedByDescending { it.periodEnd })
                }
        }
    }
}