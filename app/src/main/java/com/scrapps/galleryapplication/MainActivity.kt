package com.scrapps.galleryapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.scrapps.galleryapplication.R
import com.scrapps.galleryapplication.presentation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    internal lateinit var navigator: Navigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.showMain(this)
    }
}