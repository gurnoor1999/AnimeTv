package com.example.animetv.ui.screens

import android.content.Intent
import android.net.Uri
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.animetv.ViewModel.AnimeDetailViewModel

@Composable
fun DetailScreen(animeId: Int) {
    val viewModel: AnimeDetailViewModel = viewModel()

    val animeDetail = viewModel.animeDetails
    val isLoading = viewModel.isLoading
    val context = LocalContext.current

    val background = MaterialTheme.colors.background
    LaunchedEffect(animeId) {
        viewModel.fetchAnimeDetail(animeId)
    }

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(background),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }


    animeDetail.let { detail ->
        LazyColumn(
            modifier = Modifier
                .background(background)
                .padding(16.dp)
        ) {
            item {

                // Trailer
                if (!detail.value?.trailer?.embed_url.isNullOrEmpty()) {

                    detail.value?.trailer?.embed_url?.let { trailerUrl ->
                        AndroidView(
                            factory = {
                                WebView(it).apply {
                                    settings.javaScriptEnabled = true
                                    settings.domStorageEnabled = true
                                    settings.mediaPlaybackRequiresUserGesture = true
                                    settings.pluginState = WebSettings.PluginState.ON
                                    webChromeClient = WebChromeClient()
                                    setLayerType(android.view.View.LAYER_TYPE_HARDWARE, null)
                                    loadUrl(trailerUrl)
                                }
                            }, modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clickable {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl))
                                    context.startActivity(intent)
                                }
                        )
                    }

                } else {
                    detail.value?.images?.jpg?.image_url?.let { url ->
                        Image(
                            painter = rememberAsyncImagePainter(url),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Title
                detail.value?.title?.let { title ->
                    Text(
                        title,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h6,
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                // Synopsis
                Text(
                    "Synopsis:",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                detail.value?.synopsis?.let { Text(it, style = MaterialTheme.typography.body2) }

                // Genres
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Genres: ",
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold
                    )
                    (detail.value?.genres ?: emptyList()).forEach { genre ->
                        Text("${genre.name}, ", style = MaterialTheme.typography.body2)
                    }
                }

                //Main Cast
//                Spacer(modifier = Modifier.height(8.dp))
//                Row {
//                    Text("Main Cast: ", style = MaterialTheme.typography.body1)
//                }

                // NOT ABLE TO FIND MAIN CAST IN API

                // Episodes
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "No. of Episodes: ",
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold
                    )
                    detail.value?.episodes?.let {
                        Text("$it", style = MaterialTheme.typography.body2)
                    }
                }

                // Rating
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Rating: ",
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold
                    )
                    detail.value?.score?.let {
                        Text("$it", style = MaterialTheme.typography.body2)
                    }
                }
            }
        }
    }
}