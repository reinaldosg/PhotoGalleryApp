package com.scrapps.galleryapplication.data.remote

import com.scrapps.galleryapplication.data.remote.entity.PhotosResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {
    @GET("photos")
    fun loadPhotos(
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Call<List<PhotosResponse>>

    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
    }
}