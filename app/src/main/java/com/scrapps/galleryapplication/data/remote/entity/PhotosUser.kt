package com.scrapps.galleryapplication.data.remote.entity

import com.squareup.moshi.Json

data class PhotosUser(
    @field:Json(name= "name") val name:String,
)
