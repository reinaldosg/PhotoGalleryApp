package com.scrapps.galleryapplication.presentation.adapter

import android.content.Context
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.scrapps.galleryapplication.R
import com.scrapps.galleryapplication.databinding.PhotoLayoutListItemBinding
import com.scrapps.galleryapplication.domain.model.PhotoModel

class PhotoViewHolder (val binding: PhotoLayoutListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        context: Context,
        entity: PhotoModel?
    ) {
        binding.data = entity
        Glide.with(context)
            .load(entity?.imageUrl)
            .into(binding.image)
    }
}