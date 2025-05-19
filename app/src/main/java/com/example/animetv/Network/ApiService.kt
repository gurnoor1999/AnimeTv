package com.example.animetv.Network

import com.example.animetv.Model.AnimeDetailsResponse
import com.example.animetv.Model.AnimeListModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("top/anime")
    suspend fun getTopAnime(@Query("page") page: Int): AnimeListModel

    @GET("anime/{id}")
    suspend fun getAnimeDetails(@Path("id") id: Int): AnimeDetailsResponse
}