package com.scrapps.galleryapplication.presentation

import android.content.Context
import com.scrapps.galleryapplication.presentation.listphotos.PhotosActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor(){
    fun showMain(context: Context) {
        context.startActivity(PhotosActivity.callingIntent(context))
    }
}