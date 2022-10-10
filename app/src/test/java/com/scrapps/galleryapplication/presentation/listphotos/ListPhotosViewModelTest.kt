package com.scrapps.galleryapplication.presentation.listphotos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.scrapps.galleryapplication.*
import com.scrapps.galleryapplication.data.remote.UnsplashApi
import com.scrapps.galleryapplication.data.remote.entity.PhotosResponse
import com.scrapps.galleryapplication.data.remote.entity.PhotosUrl
import com.scrapps.galleryapplication.data.remote.entity.PhotosUser
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.*

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
        val listPhoto = listOf(
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
        val mockedCall = mockk<Call<List<PhotosResponse>>>(relaxed = true)
        every { mockedCall.enqueue(any()) } answers {
            //Get the callback from the arguments
            val callback = args[0] as Callback<List<PhotosResponse>>

            callback.onResponse(mockedCall, retrofit2.Response.success(listPhoto))
        }
        coEvery { service.loadPhotos(any(), any(), any()) } returns mockedCall

        runBlocking { listPhotosViewModel.getListPhoto() }
        Assert.assertNotNull("should not be null", listPhotosViewModel.listPhotos.getOrAwaitValue().size)

        listPhotosViewModel.listPhotos.observeForever {
            Assert.assertEquals(2,it.size)
            Assert.assertEquals("this is description", it[0]?.description)
            Assert.assertNull(it[1]?.description)
        }
    }
}