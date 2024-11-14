package com.pemeta.pemetacard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pemeta.pemetacard.data.PemetaCardRepository
import com.pemeta.pemetacard.ui.screen.experience.list.ListViewModel

class ViewModelFactory(
    private val repository: PemetaCardRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(repository) as T
        } /* else if (modelClass.isAssignableFrom(DetailRewardViewModel::class.java)) {
            return DetailRewardViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        } */
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}