package com.razzaaq.moviedb.api.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PopularMovies(
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val results: List<Result> = listOf(),
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0
) {
    @Serializable
    data class Result(
        @SerialName("adult")
        val adult: Boolean = false,
        @SerialName("gender")
        val gender: Int = 0,
        @SerialName("id")
        val id: Int = 0,
        @SerialName("known_for")
        val knownFor: List<KnownFor> = listOf(),
        @SerialName("known_for_department")
        val knownForDepartment: String = "",
        @SerialName("name")
        val name: String = "",
        @SerialName("popularity")
        val popularity: Double = 0.0,
        @SerialName("profile_path")
        val profilePath: String = ""
    ) {
        @Serializable
        data class KnownFor(
            @SerialName("adult")
            val adult: Boolean = false,
            @SerialName("backdrop_path")
            val backdropPath: String = "",
            @SerialName("first_air_date")
            val firstAirDate: String = "",
            @SerialName("genre_ids")
            val genreIds: List<Int> = listOf(),
            @SerialName("id")
            val id: Int = 0,
            @SerialName("media_type")
            val mediaType: String = "",
            @SerialName("name")
            val name: String = "",
            @SerialName("origin_country")
            val originCountry: List<String> = listOf(),
            @SerialName("original_language")
            val originalLanguage: String = "",
            @SerialName("original_name")
            val originalName: String = "",
            @SerialName("original_title")
            val originalTitle: String = "",
            @SerialName("overview")
            val overview: String = "",
            @SerialName("poster_path")
            val posterPath: String = "",
            @SerialName("release_date")
            val releaseDate: String = "",
            @SerialName("title")
            val title: String = "",
            @SerialName("video")
            val video: Boolean = false,
            @SerialName("vote_average")
            val voteAverage: Double = 0.0,
            @SerialName("vote_count")
            val voteCount: Int = 0
        )
    }
}