package com.scrapps.galleryapplication.core.extension

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.scrapps.galleryapplication.core.presentation.BaseActivity
import com.scrapps.galleryapplication.core.presentation.BaseFragment

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

fun BaseFragment.close() = fragmentManager?.popBackStack()

val BaseFragment.viewContainer: View get() = (activity as BaseActivity).binding.fragmentContainer

val BaseFragment.appContext: Context get() = activity?.applicationContext!!