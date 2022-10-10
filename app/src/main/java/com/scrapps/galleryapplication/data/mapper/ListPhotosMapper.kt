package com.scrapps.galleryapplication.data.mapper

import com.scrapps.galleryapplication.data.remote.entity.PhotosResponse
import com.scrapps.galleryapplication.domain.model.PhotoModel

fun PhotosResponse.toPhotoModel(): PhotoModel{
    return PhotoModel(
        id = id,
        imageUrl = url.thumb,
        name = user.name,
        description = description,
    )
}

