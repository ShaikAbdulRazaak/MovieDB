package com.razzaaq.moviedb.api.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpcomingMoviesDto(
    @SerialName("dates")
    val dates: Dates = Dates(),
    @SerialName("page")
    val page: Int = 0, // 1
    @SerialName("results")
    val results: List<Result> = listOf(),
    @SerialName("total_pages")
    val totalPages: Int = 0, // 44
    @SerialName("total_results")
    val totalResults: Int = 0 // 871
) {
    @Serializable
    data class Dates(
        @SerialName("maximum")
        val maximum: String = "", // 2026-08-12
        @SerialName("minimum")
        val minimum: String = "" // 2026-07-22
    )

    @Serializable
    data class Result(
        @SerialName("adult")
        val adult: Boolean = false, // false
        @SerialName("backdrop_path")
        val backdropPath: String = "", // /r57L2UBLPKcHdZQYg8tagv9XqK2.jpg
        @SerialName("genre_ids")
        val genreIds: List<Int> = listOf(),
        @SerialName("id")
        val id: Int = 0, // 1368337
        @SerialName("original_language")
        val originalLanguage: String = "", // en
        @SerialName("original_title")
        val originalTitle: String = "", // The Odyssey
        @SerialName("overview")
        val overview: String = "", // Odysseus, the legendary King of Ithaca, embarks on a long and perilous journey home following the Trojan War. Throughout his voyage, he is forced to confront the whims of gods, mythological monsters, and trials that stretch both his cunning and his humanity to the breaking point.
        @SerialName("popularity")
        val popularity: Double = 0.0, // 660.4968
        @SerialName("poster_path")
        val posterPath: String = "", // /5rhTDKUhPYvpdQIijFIs5VoWsON.jpg
        @SerialName("release_date")
        val releaseDate: String = "", // 2026-07-15
        @SerialName("softcore")
        val softcore: Boolean = false, // false
        @SerialName("title")
        val title: String = "", // The Odyssey
        @SerialName("video")
        val video: Boolean = false, // false
        @SerialName("vote_average")
        val voteAverage: Double = 0.0, // 7.742
        @SerialName("vote_count")
        val voteCount: Int = 0 // 601
    )
}