package com.scrapps.galleryapplication.domain.source

import com.scrapps.galleryapplication.data.remote.UnsplashApi
import com.scrapps.galleryapplication.data.remote.entity.PhotosResponse
import androidx.paging.PageKeyedDataSource
import com.scrapps.galleryapplication.data.mapper.toPhotoModel
import com.scrapps.galleryapplication.domain.model.PhotoModel
import com.scrapps.galleryapplication.utils.Keys
import com.scrapps.galleryapplication.utils.retroThen

class LoadPhotoSource constructor(
    private val service: UnsplashApi
) :
    PageKeyedDataSource<Int, PhotoModel>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, PhotoModel>) {
        service.loadPhotos(com.scrapps.galleryapplication.utils.getKey(), 1, params.requestedLoadSize).retroThen { response ->
            if (response.body() != null) {
                response.body()?.let {
                    callback.onResult(it.map { photosResponse -> photosResponse.toPhotoModel() }, null, 2)
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PhotoModel>) {
        service.loadPhotos(com.scrapps.galleryapplication.utils.getKey(), page = params.key, params.requestedLoadSize).retroThen { response ->
            if (response.body() != null) {
                response.body()?.let {
                    //page number based on current call result
                    val page = (if (it.isNotEmpty()) params.key + 1 else null)?.toInt()
                    callback.onResult(it.map { photosResponse -> photosResponse.toPhotoModel()}, page)
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PhotoModel>) {

    }

}
