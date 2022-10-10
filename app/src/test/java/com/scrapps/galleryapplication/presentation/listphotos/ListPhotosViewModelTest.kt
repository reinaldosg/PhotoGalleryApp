package com.scrapps.galleryapplication.presentation.listphotos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagedList
import com.scrapps.galleryapplication.*
import com.scrapps.galleryapplication.data.remote.UnsplashApi
import com.scrapps.galleryapplication.data.remote.entity.PhotosResponse
import com.scrapps.galleryapplication.data.remote.entity.PhotosUrl
import com.scrapps.galleryapplication.data.remote.entity.PhotosUser
import com.scrapps.galleryapplication.domain.model.PhotoModel
import com.scrapps.galleryapplication.domain.source.LoadPhotoSource
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runners.JUnit4
import retrofit2.Call
class ListPhotosViewModelTest : UnitTest() {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var listPhotosViewModel: ListPhotosViewModel

    @MockK
    private lateinit var service: UnsplashApi

    @Before
    fun setUp() {
        listPhotosViewModel = ListPhotosViewModel(service)
    }

    @Test
    fun `loading photos should update live data`() {
        val photoList = listOf(
            PhotosResponse(
            "VvhC97pYcUQ",
                "this is description",
                PhotosUrl(
                    raw= "https://images.unsplash.com/photo-1665236570466-2582e23a4025?ixid=MnwzNzAzODN8MHwxfGFsbHw4fHx8fHx8Mnx8MTY2NTMxNzI4Nw&ixlib=rb-1.2.1",
                    full= "https://images.unsplash.com/photo-1665236570466-2582e23a4025?crop=entropy&cs=tinysrgb&fm=jpg&ixid=MnwzNzAzODN8MHwxfGFsbHw4fHx8fHx8Mnx8MTY2NTMxNzI4Nw&ixlib=rb-1.2.1&q=80",
                    regular= "https://images.unsplash.com/photo-1665236570466-2582e23a4025?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzAzODN8MHwxfGFsbHw4fHx8fHx8Mnx8MTY2NTMxNzI4Nw&ixlib=rb-1.2.1&q=80&w=1080",
                    small= "https://images.unsplash.com/photo-1665236570466-2582e23a4025?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzAzODN8MHwxfGFsbHw4fHx8fHx8Mnx8MTY2NTMxNzI4Nw&ixlib=rb-1.2.1&q=80&w=400",
                    thumb= "https://images.unsplash.com/photo-1665236570466-2582e23a4025?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzAzODN8MHwxfGFsbHw4fHx8fHx8Mnx8MTY2NTMxNzI4Nw&ixlib=rb-1.2.1&q=80&w=200",
                    small_s3= "https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1665236570466-2582e23a4025"),
                PhotosUser("Madison Kuhn")
            ),
            PhotosResponse(
                "6jhXoH1juas",
                null,
                PhotosUrl(
                    raw= "https://images.unsplash.com/photo-1665236570466-2582e23a4025?ixid=MnwzNzAzODN8MHwxfGFsbHw4fHx8fHx8Mnx8MTY2NTMxNzI4Nw&ixlib=rb-1.2.1",
                    full= "https://images.unsplash.com/photo-1665236570466-2582e23a4025?crop=entropy&cs=tinysrgb&fm=jpg&ixid=MnwzNzAzODN8MHwxfGFsbHw4fHx8fHx8Mnx8MTY2NTMxNzI4Nw&ixlib=rb-1.2.1&q=80",
                    regular= "https://images.unsplash.com/photo-1665236570466-2582e23a4025?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzAzODN8MHwxfGFsbHw4fHx8fHx8Mnx8MTY2NTMxNzI4Nw&ixlib=rb-1.2.1&q=80&w=1080",
                    small= "https://images.unsplash.com/photo-1665236570466-2582e23a4025?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzAzODN8MHwxfGFsbHw4fHx8fHx8Mnx8MTY2NTMxNzI4Nw&ixlib=rb-1.2.1&q=80&w=400",
                    thumb= "https://images.unsplash.com/photo-1665236570466-2582e23a4025?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzAzODN8MHwxfGFsbHw4fHx8fHx8Mnx8MTY2NTMxNzI4Nw&ixlib=rb-1.2.1&q=80&w=200",
                    small_s3= "https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1665236570466-2582e23a4025"),
                PhotosUser("Madison Kuhn")
            )
        )
        coEvery { service.loadPhotos(any(), any(), any()) } returns MockCall.buildSuccess(photoList)
        runBlocking { listPhotosViewModel.getListPhoto() }
        Assert.assertEquals(2, listPhotosViewModel.listPhotos.getOrAwaitValue().size)

//        listPhotosViewModel.listPhotos.observeForever {
//            Assert.assertEquals(it.size,0)
//            Assert.assertEquals(it[0]?.description, "this is description")
//            Assert.assertNull(it[1]?.description)
//        }
    }
}