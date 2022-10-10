package com.scrapps.galleryapplication.domain.source

import androidx.paging.DataSource
import androidx.paging.PagedList
import com.scrapps.galleryapplication.data.remote.UnsplashApi
import com.scrapps.galleryapplication.data.remote.entity.PhotosResponse
import com.scrapps.galleryapplication.domain.model.PhotoModel

class LoadPhotoSourceFactory constructor(
    service: UnsplashApi
) : DataSource.Factory<Int, PhotoModel>() {

    private val source: LoadPhotoSource = LoadPhotoSource(service)

    override fun create(): DataSource<Int, PhotoModel> {
        return source
    }
}


class LoadPhotoDataSourceBoundaryCallback(private val onEmptyLoaded: () -> Unit) :
    PagedList.BoundaryCallback<PhotoModel>() {
    override fun onZeroItemsLoaded() = onEmptyLoaded.invoke()
    override fun onItemAtEndLoaded(itemAtEnd: PhotoModel) {
        itemAtEnd.toString()
    }
}