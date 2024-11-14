package com.pemeta.pemetacard.ui.screen.experience.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemeta.pemetacard.data.PemetaCardRepository
import com.pemeta.pemetacard.model.CompanyExperience
import com.pemeta.pemetacard.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailExperienceViewModel(private val repository: PemetaCardRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CompanyExperience>> =
        MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<CompanyExperience>> get() = _uiState

    fun getExperienceById(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getExperienceById(experienceId = id))
        }
    }

    fun getDataExperienceById(id: Int): CompanyExperience {
        return repository.getExperienceById(experienceId = id)
    }
}