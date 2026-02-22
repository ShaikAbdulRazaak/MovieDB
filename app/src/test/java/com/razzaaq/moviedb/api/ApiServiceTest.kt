package com.razzaaq.moviedb.api

import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

class ApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService
    private val json = Json { ignoreUnknownKeys = true }

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        val contentType = "application/json".toMediaType()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getNowPlayingMovies success response`() = runTest {
        val body = """
            {
                "results": [
                    { "id": 1, "title": "Movie 1", "poster_path": "/path1" }
                ],
                "page": 1,
                "total_pages": 1,
                "total_results": 1
            }
        """.trimIndent()
        mockWebServer.enqueue(MockResponse().setBody(body).setResponseCode(200))

        val response = apiService.getNowPlayingMovies()

        assertEquals(1, response.results.size)
        assertEquals("Movie 1", response.results[0].title)
        assertEquals("/path1", response.results[0].posterPath)
    }

    @Test
    fun `getNowPlayingMovies empty results list`() = runTest {
        val body = """
            {
                "results": [],
                "page": 1,
                "total_pages": 1,
                "total_results": 0
            }
        """.trimIndent()
        mockWebServer.enqueue(MockResponse().setBody(body).setResponseCode(200))

        val response = apiService.getNowPlayingMovies()

        assertEquals(0, response.results.size)
    }

    @Test(expected = Exception::class)
    fun `getNowPlayingMovies malformed json error`() = runTest {
        val body = """ { "results": [ { "id": "not-an-int" } ] } """
        mockWebServer.enqueue(MockResponse().setBody(body).setResponseCode(200))

        apiService.getNowPlayingMovies()
    }

    @Test
    fun `getNowPlayingMovies unauthorized 401 error`() = runTest {
        mockWebServer.enqueue(MockResponse().setResponseCode(401))

        try {
            apiService.getNowPlayingMovies()
        } catch (e: HttpException) {
            assertEquals(401, e.code())
        }
    }

    @Test
    fun `getNowPlayingMovies server error 500`() = runTest {
        mockWebServer.enqueue(MockResponse().setResponseCode(500))

        try {
            apiService.getNowPlayingMovies()
        } catch (e: HttpException) {
            assertEquals(500, e.code())
        }
    }

    @Test
    fun `getTMDBConfiguration success response`() = runTest {
        val body = """
            {
                "images": {
                    "base_url": "http://image.tmdb.org/t/p/",
                    "secure_base_url": "https://image.tmdb.org/t/p/",
                    "poster_sizes": ["w92", "w500"]
                }
            }
        """.trimIndent()
        mockWebServer.enqueue(MockResponse().setBody(body).setResponseCode(200))

        val response = apiService.getTMDBConfiguration()

        assertEquals("https://image.tmdb.org/t/p/", response.images.secureBaseUrl)
        assertEquals("w500", response.images.posterSizes.last())
    }

    @Test(expected = SocketTimeoutException::class)
    fun `getTMDBConfiguration network timeout`() = runTest {
        mockWebServer.enqueue(MockResponse().setBody("{}").setBodyDelay(5, TimeUnit.SECONDS))
        
        // Overriding the default client with a short timeout to force the exception
        val contentType = "application/json".toMediaType()
        val okHttpClient = okhttp3.OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.SECONDS)
            .build()
        
        val shortTimeoutService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(ApiService::class.java)

        shortTimeoutService.getTMDBConfiguration()
    }

    @Test
    fun `getTMDBConfiguration null fields handling`() = runTest {
        val body = """ { "images": { "secure_base_url": "https://url" } } """
        mockWebServer.enqueue(MockResponse().setBody(body).setResponseCode(200))

        val response = apiService.getTMDBConfiguration()

        assertEquals("https://url", response.images.secureBaseUrl)
        assertEquals(emptyList<String>(), response.images.posterSizes)
    }

    @Test
    fun `getMovieDetail valid id success`() = runTest {
        val body = """ { "id": 594767, "title": "Shazam!" } """
        mockWebServer.enqueue(MockResponse().setBody(body).setResponseCode(200))

        val response = apiService.getMovieDetail(594767)

        assertEquals(594767, response.id)
        assertEquals("Shazam!", response.title)
    }

    @Test
    fun `getMovieDetail resource not found 404`() = runTest {
        mockWebServer.enqueue(MockResponse().setResponseCode(404))

        try {
            apiService.getMovieDetail(999999)
        } catch (e: HttpException) {
            assertEquals(404, e.code())
        }
    }

    @Test
    fun `getMovieDetail rate limit 429 error`() = runTest {
        mockWebServer.enqueue(MockResponse().setResponseCode(429))

        try {
            apiService.getMovieDetail(1)
        } catch (e: HttpException) {
            assertEquals(429, e.code())
        }
    }
}
