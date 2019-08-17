package com.sun.funnytoeic.data.remote

import com.sun.funnytoeic.BuildConfig.BING_SEARCH_API_KEY
import com.sun.funnytoeic.data.remote.response.BingResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(API_PATH)
    suspend fun searchImageByKeywordAsync(
        @Query(KEY_SEARCH_TERM) keyword: String,
        @Query(KEY_LICENSE) license: String = VAL_LICENSE_PUBLIC,
        @Query(KEY_IMG_TYPE) imageType: String = VAL_IMG_TYPE_PHOTO
    ): BingResponse

    companion object {

        private const val HOST_NAME = "https://api.cognitive.microsoft.com"
        private const val API_PATH = "/bing/v5.0/images/search"
        private const val KEY_API_KEY = "Ocp-Apim-Subscription-Key"
        private const val KEY_SEARCH_TERM = "q"
        private const val KEY_LICENSE = "license"
        private const val VAL_LICENSE_PUBLIC = "public"
        private const val KEY_IMG_TYPE = "imageType"
        private const val VAL_IMG_TYPE_PHOTO = "photo"

        fun create(): ApiService = Retrofit.Builder()
            .baseUrl(HOST_NAME)
            .addConverterFactory(GsonConverterFactory.create())
            .client(buildClient())
            .build()
            .create(ApiService::class.java)

        private fun buildClient() = OkHttpClient.Builder()
            .addNetworkInterceptor { chain -> chain.proceed(buildRequest(chain)) }
            .build()

        private fun buildRequest(chain: Interceptor.Chain) = chain.request()
            .newBuilder()
            .addHeader(KEY_API_KEY, BING_SEARCH_API_KEY)
            .build()
    }
}
