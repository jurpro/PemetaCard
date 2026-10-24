package com.pemeta.pemetacard.ui.screen.legal.list.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemeta.pemetacard.data.repository.PemetaCardRepository
import com.pemeta.pemetacard.model.UiState
import com.pemeta.pemetacard.model.lagilitas.model.Legalitas
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ListLegalitasViewModel(private val repository: PemetaCardRepository) : ViewModel() {

    private val _uiStateLegal: MutableStateFlow<UiState<List<Legalitas>>> = MutableStateFlow(
        UiState.Loading)

    val uiStateLegal: StateFlow<UiState<List<Legalitas>>>
        get() = _uiStateLegal

    fun getListLegalitas() {
        viewModelScope.launch {
            repository.getListLegal()
                .catch {
                    _uiStateLegal.value = UiState.Error(it.message.toString())
                }
                .collect { listLegalitas ->
                    _uiStateLegal.value = UiState.Success(listLegalitas)
                }
        }
    }
}