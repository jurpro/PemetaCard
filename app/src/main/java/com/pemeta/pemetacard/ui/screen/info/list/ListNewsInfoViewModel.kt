package com.pemeta.pemetacard.ui.screen.info.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemeta.pemetacard.data.repository.PemetaCardRepository
import com.pemeta.pemetacard.model.UiState
import com.pemeta.pemetacard.model.newsinfo.model.NewsInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ListNewsInfoViewModel(private val repository: PemetaCardRepository): ViewModel() {

    private val _uiStateNews: MutableStateFlow<UiState<List<NewsInfo>>> = MutableStateFlow(UiState.Loading)
    private val _uiStateHeadline: MutableStateFlow<UiState<List<NewsInfo>>> = MutableStateFlow(UiState.Loading)
    private val _uiStateNewsByExperience: MutableStateFlow<UiState<List<NewsInfo>>> = MutableStateFlow(UiState.Loading)

    val uiStateNews: StateFlow<UiState<List<NewsInfo>>>
        get() = _uiStateNews

    val uiStateHeadline: StateFlow<UiState<List<NewsInfo>>>
        get() = _uiStateHeadline

    val uiStateNewsByExperience: StateFlow<UiState<List<NewsInfo>>>
        get() = _uiStateNewsByExperience

    fun getListNewsInfos() {
        viewModelScope.launch {
            repository.getListNewsInfo()
                .catch {
                    _uiStateNews.value = UiState.Error(it.message.toString())
                }
                .collect { listNewsInfos ->
                    _uiStateNews.value = UiState.Success(listNewsInfos.sortedByDescending { it.date })
                }
        }
    }

    fun getListHeadlines() {
        viewModelScope.launch {
            repository.getListHeadline()
                .catch {
                    _uiStateHeadline.value = UiState.Error(it.message.toString())
                }
                .collect { listHeadline ->
                    _uiStateHeadline.value = UiState.Success(listHeadline.sortedByDescending { it.date })
                }
        }
    }

    fun getListNewsByExperiences(experienceId:Int) {
        viewModelScope.launch {
            repository.getListNewsByExperience(experienceId)
                .catch {
                    _uiStateNewsByExperience.value = UiState.Error(it.message.toString())
                }
                .collect { listNewsInfos ->
                    _uiStateNewsByExperience.value = UiState.Success(listNewsInfos.sortedByDescending { it.date })
                }
        }
    }
}