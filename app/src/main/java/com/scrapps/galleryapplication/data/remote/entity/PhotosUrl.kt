package com.scrapps.galleryapplication.data.remote.entity

import com.squareup.moshi.Json

data class PhotosUrl(
    @field:Json(name= "raw") val raw:String,
    @field:Json(name= "full") val full:String,
    @field:Json(name= "regular") val regular:String,
    @field:Json(name= "small") val small:String,
    @field:Json(name= "thumb") val thumb:String,
    @field:Json(name= "small_s3") val small_s3:String,
)
