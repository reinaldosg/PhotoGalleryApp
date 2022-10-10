package com.scrapps.galleryapplication.utils

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun <T> Call<T>.retroThen(onResponse: (Response<T>) -> Unit) {
    this.enqueue(retroCallback(onResponse))
}

fun <T> Call<T>.retroThen(onResponse: (Response<T>) -> Unit, onFailure: (Throwable) -> Unit) {
    this.enqueue(retroCallback(onResponse, onFailure))
}


fun <T> retroCallback(onResponse: (Response<T>) -> Unit): Callback<T> {
    return object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {

        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            onResponse.invoke(response)
        }
    }
}

fun <T> retroCallback(onResponse: (Response<T>) -> Unit, onFailure: ((Throwable) -> Unit)?): Callback<T> {
    return object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {

            onFailure?.invoke(t)
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            onResponse.invoke(response)
        }
    }
}


fun getKey():String{
    var key = ""
    try{
        key = Keys.accessKey()
    } catch(e:UnsatisfiedLinkError){

    }
    return key
}