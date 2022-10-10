package com.scrapps.galleryapplication.data.remote.entity

import com.squareup.moshi.Json

data class PhotosResponse(
    @field:Json(name= "id") val id:String,
    @field:Json(name= "description") val description:String?,
    @field:Json(name= "urls") val url: PhotosUrl,
    @field:Json(name= "user") val user: PhotosUser
)
