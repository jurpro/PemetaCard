package com.pemeta.pemetacard.ui.screen.experience.typeJob.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemeta.pemetacard.data.repository.PemetaCardRepository
import com.pemeta.pemetacard.model.UiState
import com.pemeta.pemetacard.model.pengalaman.model.CompanyExperience
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ExperienceByTypeViewModel(private val repository: PemetaCardRepository) : ViewModel() {

    private val _uiStateExperienceByType: MutableStateFlow<UiState<List<CompanyExperience>>> = MutableStateFlow(UiState.Loading)
    private val _uiStateFinishedExperienceByType: MutableStateFlow<UiState<List<CompanyExperience>>> = MutableStateFlow(UiState.Loading)
    private val _uiStateOnGoingExperienceByType: MutableStateFlow<UiState<List<CompanyExperience>>> = MutableStateFlow(UiState.Loading)

    val uiStateExperienceByType: StateFlow<UiState<List<CompanyExperience>>> get() = _uiStateExperienceByType
    val uiStateFinishedExperienceByType: StateFlow<UiState<List<CompanyExperience>>> get() = _uiStateFinishedExperienceByType
    val uiStateOnGoingExperienceByType: StateFlow<UiState<List<CompanyExperience>>> get() = _uiStateOnGoingExperienceByType

    fun getListExperienceByType(typeId:Int) {
        viewModelScope.launch {
            repository.getListExperienceByType(typeId)
                .catch {
                    _uiStateExperienceByType.value = UiState.Error(it.message.toString())
                }
                .collect { listExperienceByType ->
                    _uiStateExperienceByType.value = UiState.Success(listExperienceByType.sortedByDescending { it.periodEnd })
                }
        }
    }

    fun getListFinishedByType(typeId:Int) {
        viewModelScope.launch {
            repository.getListFinishedByType(typeId)
                .catch {
                    _uiStateFinishedExperienceByType.value = UiState.Error(it.message.toString())
                }
                .collect { listExperienceByType ->
                    _uiStateFinishedExperienceByType.value = UiState.Success(listExperienceByType.sortedByDescending { it.periodEnd })
                }
        }
    }

    fun getListOnGoingByType(typeId:Int) {
        viewModelScope.launch {
            repository.getListOnGoingByType(typeId)
                .catch {
                    _uiStateOnGoingExperienceByType.value = UiState.Error(it.message.toString())
                }
                .collect { listExperienceByType ->
                    _uiStateOnGoingExperienceByType.value = UiState.Success(listExperienceByType.sortedByDescending { it.periodEnd })
                }
        }
    }
}