package com.pemeta.pemetacard.ui.screen.client.list.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemeta.pemetacard.data.repository.PemetaCardRepository
import com.pemeta.pemetacard.model.client.Client
import com.pemeta.pemetacard.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ListClientViewModel(private val repository: PemetaCardRepository) : ViewModel() {

    private val _uiStateClient: MutableStateFlow<UiState<List<Client>>> = MutableStateFlow(
        UiState.Loading)

    val uiStateClient: StateFlow<UiState<List<Client>>>
        get() = _uiStateClient

    val uiStateClientData: StateFlow<UiState<Client>>
        get() = _uiStateClientData

    private val _uiStateClientData: MutableStateFlow<UiState<Client>> = MutableStateFlow(
        UiState.Loading)

    private val _uiStateClientExperience: MutableStateFlow<UiState<Client>> =
        MutableStateFlow(UiState.Loading)

    val uiStateClientExperience: StateFlow<UiState<Client>> get() = _uiStateClientExperience

    fun getListClients() {
        viewModelScope.launch {
            repository.getListClient()
                .catch {
                    _uiStateClient.value = UiState.Error(it.message.toString())
                }
                .collect { listClients ->
                    _uiStateClient.value = UiState.Success(listClients)
                }
        }
    }

    /*
    fun getClient(clientId: Int) {
        viewModelScope.launch {
            _uiStateClientData.value = UiState.Loading
            _uiStateClientData.value = UiState.Success(repository.getClientById(clientId = clientId))
        }
    }

    fun getClientExperience(clientExperienceId: Int) {
        viewModelScope.launch {
            _uiStateClient.value = UiState.Loading
            _uiStateClient.value = UiState.Success(repository.getClientExperiencebyId(clientExperienceId = clientExperienceId))
        }
    } */
}
