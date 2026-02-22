package com.razzaaq.moviedb.api.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigurationDetail(
    @SerialName("change_keys")
    val changeKeys: List<String> = listOf(),
    @SerialName("images")
    val images: Images = Images()
) {
    @Serializable
    data class Images(
        @SerialName("backdrop_sizes")
        val backdropSizes: List<String> = listOf(),
        @SerialName("base_url")
        val baseUrl: String = "",
        @SerialName("logo_sizes")
        val logoSizes: List<String> = listOf(),
        @SerialName("poster_sizes")
        val posterSizes: List<String> = listOf(),
        @SerialName("profile_sizes")
        val profileSizes: List<String> = listOf(),
        @SerialName("secure_base_url")
        val secureBaseUrl: String = "",
        @SerialName("still_sizes")
        val stillSizes: List<String> = listOf()
    )
}

class ConfigurationCountries : ArrayList<ConfigurationCountries.ConfigurationCountriesItem>() {
    @Serializable
    data class ConfigurationCountriesItem(
        @SerialName("english_name")
        val englishName: String = "", // Andorra
        @SerialName("iso_3166_1")
        val iso31661: String = "", // AD
        @SerialName("native_name")
        val nativeName: String = "" // Andorra
    )
}

class ConfigurationJobs : ArrayList<ConfigurationJobs.ConfigurationJobsItem>() {
    @Serializable
    data class ConfigurationJobsItem(
        @SerialName("department")
        val department: String = "", // Production
        @SerialName("jobs")
        val jobs: List<String> = listOf()
    )
}

class ConfigurationLanguages : ArrayList<ConfigurationLanguages.ConfigurationLanguagesItem>() {
    @Serializable
    data class ConfigurationLanguagesItem(
        @SerialName("english_name")
        val englishName: String = "", // Bislama
        @SerialName("iso_639_1")
        val iso6391: String = "", // bi
        @SerialName("name")
        val name: String = ""
    )
}

class ConfigurationTranslations : ArrayList<String>()
class ConfigurationTimeZones : ArrayList<ConfigurationTimeZones.ConfigurationTimeZonesItem>() {
    @Serializable
    data class ConfigurationTimeZonesItem(
        @SerialName("iso_3166_1")
        val iso31661: String = "", // AD
        @SerialName("zones")
        val zones: List<String> = listOf()
    )
}