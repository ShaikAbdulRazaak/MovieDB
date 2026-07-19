package com.razzaaq.moviedb.api.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopRatedMoviesDto(
    @SerialName("page")
    val page: Int = 0, // 1
    @SerialName("results")
    val results: List<Result> = listOf(),
    @SerialName("total_pages")
    val totalPages: Int = 0, // 552
    @SerialName("total_results")
    val totalResults: Int = 0 // 11025
) {
    @Serializable
    data class Result(
        @SerialName("adult")
        val adult: Boolean = false, // false
        @SerialName("backdrop_path")
        val backdropPath: String = "", // /iCML6fBe3X0y06P33bM7dyYXPKZ.jpg
        @SerialName("genre_ids")
        val genreIds: List<Int> = listOf(),
        @SerialName("id")
        val id: Int = 0, // 1632181
        @SerialName("original_language")
        val originalLanguage: String = "", // es
        @SerialName("original_title")
        val originalTitle: String = "", // Socias por accidente
        @SerialName("overview")
        val overview: String = "", // Two women discover they were both scammed by the same man (who also got them pregnant). They form an alliance to take revenge.
        @SerialName("popularity")
        val popularity: Double = 0.0, // 27.8439
        @SerialName("poster_path")
        val posterPath: String = "", // /sr1Yoj6XLPTWYcbDoBy67xivW4I.jpg
        @SerialName("release_date")
        val releaseDate: String = "", // 2026-03-12
        @SerialName("softcore")
        val softcore: Boolean = false, // false
        @SerialName("title")
        val title: String = "", // Accidental Partners
        @SerialName("video")
        val video: Boolean = false, // false
        @SerialName("vote_average")
        val voteAverage: Double = 0.0, // 9.048
        @SerialName("vote_count")
        val voteCount: Int = 0 // 312
    )
}