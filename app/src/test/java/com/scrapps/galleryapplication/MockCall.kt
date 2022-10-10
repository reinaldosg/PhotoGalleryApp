package com.scrapps.galleryapplication

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import retrofit2.Call
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Callback
import retrofit2.Response

class MockCall<T>(
    private val response: Response<T>
)
    : Call<T> {

    companion object {
        inline fun <reified T> buildSuccess(body: T): MockCall<T> {
            return MockCall(Response.success(body))
        }

        inline fun <reified T> buildHttpError(errorCode: Int, contentType: String, content: String): MockCall<T> {
            return MockCall(Response.error(errorCode, ResponseBody.create(contentType.toMediaTypeOrNull(), content)))
        }
    }

    override fun execute(): Response<T> = response

    override fun enqueue(callback: Callback<T>?) {}

    override fun isExecuted(): Boolean = false

    override fun clone(): Call<T> = this

    override fun isCanceled(): Boolean = false

    override fun cancel() {}

    override fun request(): Request? = null
    override fun timeout(): Timeout {
        return Timeout()
    }
}