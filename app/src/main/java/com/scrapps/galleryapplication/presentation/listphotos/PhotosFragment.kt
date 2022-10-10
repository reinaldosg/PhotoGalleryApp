package com.scrapps.galleryapplication.presentation.listphotos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.scrapps.galleryapplication.R
import com.scrapps.galleryapplication.core.presentation.BaseFragment
import com.scrapps.galleryapplication.databinding.FragmentPhotosBinding
import com.scrapps.galleryapplication.presentation.Navigator
import com.scrapps.galleryapplication.presentation.adapter.PhotoAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PhotosFragment : BaseFragment() {
    @Inject
    lateinit var navigator: Navigator

    override fun layoutId() = R.layout.fragment_photos

    private val viewModel by viewModels<ListPhotosViewModel>()
    private lateinit var adapter: PhotoAdapter
    private lateinit var binding: FragmentPhotosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            layoutId(),
            container,
            false
        )
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PhotoAdapter(requireActivity())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
        viewModel.getListPhoto {  }
        viewModel.listPhotos.observe(
            viewLifecycleOwner,
            Observer {
                adapter.submitList(it)
            })
    }
}