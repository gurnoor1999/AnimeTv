package com.example.animetv.Model

data class AnimeListModel(
    val pagination: Pagination,
    val data: List<AnimeDetailsModel>
)

data class Pagination(
    val last_visible_page: Int,
    val has_next_page: Boolean,
    val current_page: Int,
    val items: PageItems
)

data class PageItems(
    val count: Int,
    val total: Int,
    val per_page: Int
)

data class AnimeDetailsResponse(
    val data : AnimeDetailsModel
)

data class AnimeDetailsModel(
    val mal_id: Int,
    val title: String,
    val title_english: String?,
    val title_japanese: String?,
    val title_synonyms: List<String>,
    val synopsis: String?,
    val score: Double?,
    val episodes: Int?,
    val rating: String?,
    val images: Images,
    val trailer: Trailer?,
    val genres: List<Genre>,
    val studios: List<Studio>,
    val licensors: List<Licensor>,
    val producers: List<Producer>,
    val members: Int,
    val favorites: Int
)

data class Images(
    val jpg: JpgImage,
    val webp: WebpImage
)

data class JpgImage(
    val image_url: String,
    val small_image_url: String,
    val large_image_url: String
)

data class WebpImage(
    val image_url: String,
    val small_image_url: String,
    val large_image_url: String
)

data class Trailer(
    val youtube_id: String?,
    val url: String?,
    val embed_url: String?,
    val images: TrailerImages?
)

data class TrailerImages(
    val image_url: String?,
    val small_image_url: String?,
    val medium_image_url: String?,
    val large_image_url: String?,
    val maximum_image_url: String?
)

data class Genre(
    val mal_id: Int,
    val name: String,
    val url: String
)

data class Studio(
    val mal_id: Int,
    val name: String,
    val url: String
)

data class Licensor(
    val mal_id: Int,
    val name: String,
    val url: String
)

data class Producer(
    val mal_id: Int,
    val name: String,
    val url: String
)