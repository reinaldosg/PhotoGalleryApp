package com.scrapps.galleryapplication.presentation.listphotos

import android.content.Context
import android.content.Intent
import com.scrapps.galleryapplication.core.presentation.BaseActivity

class PhotosActivity : BaseActivity() {
    companion object {
        fun callingIntent(context: Context) = Intent(context, PhotosActivity::class.java)
    }

    override fun fragment() = PhotosFragment()

}