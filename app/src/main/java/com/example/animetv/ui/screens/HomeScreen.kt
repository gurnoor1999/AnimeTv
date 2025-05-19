package com.example.animetv.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.animetv.Model.AnimeDetailsModel
import com.example.animetv.ViewModel.AnimeListViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel : AnimeListViewModel = viewModel()

    val animeList = viewModel.animeList
    val isLoading = viewModel.isLoading
    val hasNextPage = viewModel.hasNextPage

    val background = MaterialTheme.colors.background

    LazyColumn(modifier = Modifier.fillMaxSize().background(background)) {
        itemsIndexed(animeList) { index, anime ->
            AnimeListItem(anime) {
                navController.navigate("detail/${anime.mal_id}")
            }

            if (index == animeList.lastIndex && hasNextPage && !isLoading) {
                LaunchedEffect(Unit) {
                    viewModel.loadMoreAnime()
                }
            }
        }

        if (isLoading) {
            item {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}

@Composable
fun AnimeListItem(anime: AnimeDetailsModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(anime.images.jpg.image_url),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(anime.title, fontWeight = FontWeight.Bold)
                Text("Episodes: ${anime.episodes ?: "N/A"}")
                Text("Rating: ${anime.score ?: "N/A"}")
            }
        }
    }
}