package com.razzaaq.moviedb.api.dto

data class MovieDetailUi(
    val id: Int = 0,
    val title: String = "",
    val backdropPath: String = "",
    val tagline: String = "",
    val overview: String = "",
    val budget: String = "",
    val revenue: String = "",
    val releaseDate: String = "",
    val runtime: String = "",
    val genres: String = "",
    val homepage: String = "",
    val productionCompanies: List<ProductionCompanyUi> = listOf()
)

data class ProductionCompanyUi(
    val id: Int = 0,
    val logoUrl: String = "",
    val name: String = "",
    val originCountry: String = ""
)
