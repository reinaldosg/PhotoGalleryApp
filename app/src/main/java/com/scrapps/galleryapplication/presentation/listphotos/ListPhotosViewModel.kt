package com.scrapps.galleryapplication.presentation.listphotos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.scrapps.galleryapplication.data.remote.UnsplashApi
import com.scrapps.galleryapplication.data.remote.entity.PhotosResponse
import com.scrapps.galleryapplication.domain.model.PhotoModel
import com.scrapps.galleryapplication.domain.source.LoadPhotoDataSourceBoundaryCallback
import com.scrapps.galleryapplication.domain.source.LoadPhotoSourceFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListPhotosViewModel @Inject constructor(val service: UnsplashApi) : ViewModel(){

    lateinit var listPhotos: LiveData<PagedList<PhotoModel>>

    fun getListPhoto(onEmptyLoaded:() -> Unit = {}) {
        val dataSource = LoadPhotoSourceFactory(service)
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(20)
            .setPageSize(20).build()

        val boundaryCallback = LoadPhotoDataSourceBoundaryCallback(onEmptyLoaded = onEmptyLoaded)
        listPhotos = LivePagedListBuilder(dataSource, pagedListConfig)
            .setBoundaryCallback(boundaryCallback)
            .build()
    }
}