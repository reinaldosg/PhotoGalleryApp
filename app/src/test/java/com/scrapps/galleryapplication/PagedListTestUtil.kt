package com.scrapps.galleryapplication

import androidx.paging.Config
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import com.scrapps.galleryapplication.data.mapper.toPhotoModel
import com.scrapps.galleryapplication.domain.model.PhotoModel
import io.mockk.mockk

fun <T> List<T>.asPagedList() = LivePagedListBuilder<Int, T>(createMockDataSourceFactory(this),
    Config(enablePlaceholders = false,
        prefetchDistance = 24,
        pageSize = if (size == 0) 1 else size))
    .build().getOrAwaitValue()

private fun <T> createMockDataSourceFactory(itemList: List<T>): DataSource.Factory<Int, T> =
    object : DataSource.Factory<Int, T>() {
        override fun create(): PageKeyedDataSource<Int, T> = MockLimitDataSource(itemList)
    }

class MockLimitDataSource<T>(private val itemList: List<T>)  :
    PageKeyedDataSource<Int, T>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, T>
    ) {
        callback.onResult(itemList, null, 2)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        val key = (if (itemList.isNotEmpty()) params.key + 1 else null)?.toInt()
        callback.onResult(itemList, key)
    }

}