package com.pemeta.pemetacard.ui.screen.experience.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemeta.pemetacard.data.repository.PemetaCardRepository
import com.pemeta.pemetacard.model.pengalaman.model.CompanyExperience
import com.pemeta.pemetacard.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ListViewModel(private val repository: PemetaCardRepository) : ViewModel() {

    private val _uiStateExperience: MutableStateFlow<UiState<List<CompanyExperience>>> = MutableStateFlow(UiState.Loading)
    private val _uiStatePlanningExperience: MutableStateFlow<UiState<List<CompanyExperience>>> = MutableStateFlow(UiState.Loading)
    private val _uiStateSupervisionExperience: MutableStateFlow<UiState<List<CompanyExperience>>> = MutableStateFlow(UiState.Loading)
    private val _uiStateFinishedExperience: MutableStateFlow<UiState<List<CompanyExperience>>> = MutableStateFlow(UiState.Loading)
    private val _uiStateOnGoingExperience: MutableStateFlow<UiState<List<CompanyExperience>>> = MutableStateFlow(UiState.Loading)

    val uiStateExperience: StateFlow<UiState<List<CompanyExperience>>>
        get() = _uiStateExperience
    val uiStatePlanningExperience: StateFlow<UiState<List<CompanyExperience>>>
        get() = _uiStatePlanningExperience
    val uiStateSupervisionExperience: StateFlow<UiState<List<CompanyExperience>>>
        get() = _uiStateSupervisionExperience
    val uiStateFinishedExperience: StateFlow<UiState<List<CompanyExperience>>>
        get() = _uiStateFinishedExperience
    val uiStateOnGoingExperience: StateFlow<UiState<List<CompanyExperience>>>
        get() = _uiStateOnGoingExperience

    fun getListExperiences() {
        viewModelScope.launch {
            repository.getListExperience()
                .catch {
                    _uiStateExperience.value = UiState.Error(it.message.toString())
                }
                .collect { listExperiences ->
                    _uiStateExperience.value = UiState.Success(listExperiences.sortedByDescending { it.periodEnd })
                }
        }
    }

    fun getListPlanningExperience(typeGlobalId: Int) {
        viewModelScope.launch {
            repository.getListGlobalWork(typeGlobalId)
                .catch {
                    _uiStatePlanningExperience.value = UiState.Error(it.message.toString())
                }
                .collect { listPlanningExperience ->
                    _uiStatePlanningExperience.value = UiState.Success(listPlanningExperience.sortedByDescending { it.periodEnd })
                }
        }
    }

    fun getListSupervisionExperience(typeGlobalId: Int) {
        viewModelScope.launch {
            repository.getListGlobalWork(typeGlobalId)
                .catch {
                    _uiStateSupervisionExperience.value = UiState.Error(it.message.toString())
                }
                .collect { listSupervisionExperience ->
                    _uiStateSupervisionExperience.value = UiState.Success(listSupervisionExperience.sortedByDescending { it.periodEnd })
                }
        }
    }
}