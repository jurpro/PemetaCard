package com.pemeta.pemetacard.ui.screen.team.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemeta.pemetacard.data.repository.PemetaCardRepository
import com.pemeta.pemetacard.model.UiState
import com.pemeta.pemetacard.model.team.Employee
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TeamViewModel(private val repository: PemetaCardRepository) : ViewModel() {
    private val _uiStateTeam: MutableStateFlow<UiState<List<Employee>>> = MutableStateFlow(UiState.Loading)
    private val _uiStateTeamManagement: MutableStateFlow<UiState<List<Employee>>> = MutableStateFlow(UiState.Loading)
    private val _uiStateTeamDesign: MutableStateFlow<UiState<List<Employee>>> = MutableStateFlow(UiState.Loading)
    private val _uiStateTeamSupervision: MutableStateFlow<UiState<List<Employee>>> = MutableStateFlow(UiState.Loading)

    val uiStateTeam: StateFlow<UiState<List<Employee>>>
        get() = _uiStateTeam
    val uiStateTeamManagement: StateFlow<UiState<List<Employee>>>
        get() = _uiStateTeamManagement
    val uiStateTeamDesign: StateFlow<UiState<List<Employee>>>
        get() = _uiStateTeamDesign
    val uiStateTeamSupervision: StateFlow<UiState<List<Employee>>>
        get() = _uiStateTeamSupervision

    fun getListTeam() {
        viewModelScope.launch {
            repository.getListTeam()
                .catch {
                    _uiStateTeam.value = UiState.Error(it.message.toString())
                }
                .collect { listTeam ->
                    _uiStateTeam.value = UiState.Success(listTeam)
                }
        }
    }

    fun getListTeamManagement() {
        viewModelScope.launch {
            repository.getListTeamManagement()
                .catch {
                    _uiStateTeamManagement.value = UiState.Error(it.message.toString())
                }
                .collect { listTeamManagement ->
                    _uiStateTeamManagement.value = UiState.Success(listTeamManagement)
                }
        }
    }

    fun getListDesign() {
        viewModelScope.launch {
            repository.getListTeamDesign()
                .catch {
                    _uiStateTeamDesign.value = UiState.Error(it.message.toString())
                }
                .collect { listTeamDesign ->
                    _uiStateTeamDesign.value = UiState.Success(listTeamDesign)
                }
        }
    }

    fun getListSupervision() {
        viewModelScope.launch {
            repository.getListTeamSupervision()
                .catch {
                    _uiStateTeamSupervision.value = UiState.Error(it.message.toString())
                }
                .collect { listTeamSupervision ->
                    _uiStateTeamSupervision.value = UiState.Success(listTeamSupervision)
                }
        }
    }
}