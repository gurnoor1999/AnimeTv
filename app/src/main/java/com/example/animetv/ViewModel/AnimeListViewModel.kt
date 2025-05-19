package com.example.animetv.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animetv.Network.ApiClient
import com.example.animetv.Model.AnimeDetailsModel
import kotlinx.coroutines.launch

class AnimeListViewModel : ViewModel() {
    var animeList = mutableStateListOf<AnimeDetailsModel>()
        private set

    var currentPage by mutableIntStateOf(1)
    var hasNextPage by mutableStateOf(true)
    var isLoading by mutableStateOf(false)

    init {
        loadMoreAnime()
    }

    fun loadMoreAnime() {
        if (isLoading || !hasNextPage) return

        isLoading = true

        viewModelScope.launch {
            try {
                val response = ApiClient.api.getTopAnime(currentPage)
                animeList.addAll(response.data)
                hasNextPage = response.pagination.has_next_page
                currentPage++
            } catch (e: Exception) {
                Log.e("AnimeListViewModelError", "$e")
            } finally {
                isLoading = false
            }
        }
    }
}
