package com.razzaaq.moviedb.api.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetail(
    @SerialName("adult")
    val adult: Boolean = false, // false
    @SerialName("backdrop_path")
    val backdropPath: String = "", // /kQV9sV0IbastbU5FYxuYwxfMawz.jpg
    @SerialName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection = BelongsToCollection(),
    @SerialName("budget")
    val budget: Int = 0, // 110000000
    @SerialName("genres")
    val genres: List<Genre> = listOf(),
    @SerialName("homepage")
    val homepage: String = "",
    @SerialName("id")
    val id: Int = 0, // 594767
    @SerialName("imdb_id")
    val imdbId: String = "", // tt10151854
    @SerialName("origin_country")
    val originCountry: List<String> = listOf(),
    @SerialName("original_language")
    val originalLanguage: String = "", // en
    @SerialName("original_title")
    val originalTitle: String = "", // Shazam! Fury of the Gods
    @SerialName("overview")
    val overview: String = "", // Billy Batson and his foster siblings, who transform into superheroes by saying "Shazam!", are forced to get back into action and fight the Daughters of Atlas, who they must stop from using a weapon that could destroy the world.
    @SerialName("popularity")
    val popularity: Double = 0.0, // 7.8898
    @SerialName("poster_path")
    val posterPath: String = "", // /A3ZbZsmsvNGdprRi2lKgGEeVLEH.jpg
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompany> = listOf(),
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountry> = listOf(),
    @SerialName("release_date")
    val releaseDate: String = "", // 2023-03-15
    @SerialName("revenue")
    val revenue: Int = 0, // 134221819
    @SerialName("runtime")
    val runtime: Int = 0, // 130
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage> = listOf(),
    @SerialName("status")
    val status: String = "", // Released
    @SerialName("tagline")
    val tagline: String = "", // Oh. My. Gods.
    @SerialName("title")
    val title: String = "", // Shazam! Fury of the Gods
    @SerialName("video")
    val video: Boolean = false, // false
    @SerialName("vote_average")
    val voteAverage: Double = 0.0, // 6.444
    @SerialName("vote_count")
    val voteCount: Int = 0 // 3519
) {
    @Serializable
    data class BelongsToCollection(
        @SerialName("backdrop_path")
        val backdropPath: String = "", // /8yak06wMUWUip8TdDMYc7NpntgR.jpg
        @SerialName("id")
        val id: Int = 0, // 724848
        @SerialName("name")
        val name: String = "", // Shazam! Collection
        @SerialName("poster_path")
        val posterPath: String = "" // /qZ5EoG97hmlqCfNI2VWeMGIXJPJ.jpg
    )

    @Serializable
    data class Genre(
        @SerialName("id")
        val id: Int = 0, // 35
        @SerialName("name")
        val name: String = "" // Comedy
    )

    @Serializable
    data class ProductionCompany(
        @SerialName("id")
        val id: Int = 0, // 12
        @SerialName("logo_path")
        val logoPath: String = "", // /2ycs64eqV5rqKYHyQK0GVoKGvfX.png
        @SerialName("name")
        val name: String = "", // New Line Cinema
        @SerialName("origin_country")
        val originCountry: String = "" // US
    )

    @Serializable
    data class ProductionCountry(
        @SerialName("iso_3166_1")
        val iso31661: String = "", // US
        @SerialName("name")
        val name: String = "" // United States of America
    )

    @Serializable
    data class SpokenLanguage(
        @SerialName("english_name")
        val englishName: String = "", // English
        @SerialName("iso_639_1")
        val iso6391: String = "", // en
        @SerialName("name")
        val name: String = "" // English
    )
}