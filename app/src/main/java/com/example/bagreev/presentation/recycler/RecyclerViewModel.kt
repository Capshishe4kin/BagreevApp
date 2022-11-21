package com.example.bagreev.presentation.recycler

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.example.bagreev.data.Repository
import com.example.bagreev.data.model.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecyclerViewModel(
    private val repository: Repository,
    handle: SavedStateHandle,
) : ViewModel() {

    fun getGifts(onResult: (DataResult) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getGifts()
            withContext(Dispatchers.Main) {
                onResult.invoke(result)
            }
        }
    }

    companion object {
        fun provideFactory(
            myRepository: Repository,
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null,
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    return RecyclerViewModel(myRepository, handle) as T
                }
            }
    }
}