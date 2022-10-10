package com.scrapps.galleryapplication.utils

object Keys {

    init {
        try {
            System.loadLibrary("native-lib")
        } catch (e:UnsatisfiedLinkError ) {

        }
    }

    external fun accessKey(): String
}