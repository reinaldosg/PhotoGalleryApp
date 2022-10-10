package com.scrapps.galleryapplication.presentation.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.scrapps.galleryapplication.databinding.PhotoLayoutListItemBinding
import com.scrapps.galleryapplication.domain.model.PhotoModel

class PhotoAdapter (val context: Activity) :
PagedListAdapter<PhotoModel, PhotoViewHolder>(
    PHOTO_LIST_CALLBACK
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PhotoLayoutListItemBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(context, getItem(position))
    }
    companion object {
        val PHOTO_LIST_CALLBACK =
            object : DiffUtil.ItemCallback<PhotoModel>() {
                override fun areItemsTheSame(
                    oldItem: PhotoModel,
                    newItem: PhotoModel
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: PhotoModel,
                    newItem: PhotoModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}