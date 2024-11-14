package com.pemeta.pemetacard.ui.screen.experience.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemeta.pemetacard.data.PemetaCardRepository
import com.pemeta.pemetacard.model.CompanyExperience
import com.pemeta.pemetacard.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ListViewModel(private val repository: PemetaCardRepository) : ViewModel() {

    private val _uiStateExperience: MutableStateFlow<UiState<List<CompanyExperience>>> = MutableStateFlow(UiState.Loading)

    val uiStateExperience: StateFlow<UiState<List<CompanyExperience>>>
        get() = _uiStateExperience

    fun getListExperiences() {
        viewModelScope.launch {
            repository.getListExperience()
                .catch {
                    _uiStateExperience.value = UiState.Error(it.message.toString())
                }
                .collect { listExperiences ->
                    _uiStateExperience.value = UiState.Success(listExperiences)
                }
        }
    }
}