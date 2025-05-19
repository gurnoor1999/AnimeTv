package com.example.animetv.ViewModel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animetv.Network.ApiClient
import com.example.animetv.Model.AnimeDetailsModel
import kotlinx.coroutines.launch

class AnimeDetailViewModel : ViewModel() {
    var animeDetails = mutableStateOf<AnimeDetailsModel?>(null)
        private set
    var isLoading by mutableStateOf(false)

    fun fetchAnimeDetail(animeId: Int) {
        if (isLoading) return

        isLoading = true

        viewModelScope.launch {
            try {
                val response = ApiClient.api.getAnimeDetails(animeId)
                animeDetails.value = response.data

            } catch (e: Exception) {
                Log.e("AnimeDetailViewModelError", "$e")
            } finally {
                isLoading = false
            }
        }
    }
}