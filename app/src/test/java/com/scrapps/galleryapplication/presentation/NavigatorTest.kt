package com.scrapps.galleryapplication.presentation

import com.scrapps.galleryapplication.AndroidTest
import com.scrapps.galleryapplication.MainActivity
import com.scrapps.galleryapplication.presentation.listphotos.PhotosActivity
import com.scrapps.galleryapplication.shouldNavigateTo
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class NavigatorTest : AndroidTest() {

    private lateinit var navigator: Navigator

    @Before
    fun setup() {
        navigator = Navigator()
    }

    @Test
    fun `should forward user to photo list screen`() {
        navigator.showMain(context())

        MainActivity::class shouldNavigateTo PhotosActivity::class
    }
}