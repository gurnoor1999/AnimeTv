# AnimeTV App

AnimeTV is an Android application built using Jetpack Compose. It allows users to browse a list of anime, view details of particular Anime.


# Assumptions Made

- Anime data is fetched from a REST API that returns paginated results.
- Each anime item has detail like `title`, `images`, `episodes`, and `genre`.
- Network availability is assumed; no offline caching or local database has been implemented.


# Features Implemented

- Jetpack Compose UI: Modern UI with Compose components like `LazyColumn`, `Scaffold` & `Card`.
- Navigation: Navigation using `NavController`.
- Anime List Display: Displays anime title, episode count, rating, and image in a scrollable list.
- Anime Detail Page Display: Displays anime trailer, title, synopsis, genre, etc. in this page.
- Infinite Scrolling: Automatically loads more anime when the user scrolls to the bottom of the list.


# Known Limitations

- No Error Handling: Network or parsing errors are not handled or displayed to the user.
- No Image Placeholders: Missing or failed image loads are not visually handled.
- No Search or Filter: There is no support for searching or filtering anime.


# Future Enhancements

- Add search and genre filters.
- Implement offline support with Room or DataStore.
- Include error handling for network issues.
- Add user favorites/bookmarking feature.


# Tech Stack

- Kotlin
- Jetpack Compose
- ViewModel