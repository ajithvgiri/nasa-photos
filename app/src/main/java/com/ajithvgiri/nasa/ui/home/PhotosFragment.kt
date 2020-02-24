package com.ajithvgiri.nasa.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ajithvgiri.nasa.R
import com.ajithvgiri.nasa.adapter.PhotosAdapter
import kotlinx.android.synthetic.main.fragment_photos.*

class PhotosFragment : Fragment() {

    private lateinit var photosViewModel: PhotosViewModel
    private lateinit var photosAdapter: PhotosAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        photosViewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)
        return inflater.inflate(R.layout.fragment_photos, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photosAdapter = PhotosAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GridLayoutManager(context, 2).apply {
            recyclerView.layoutManager = this
            recyclerView.adapter = photosAdapter
        }

        photosViewModel.listOfPhotos.observe(viewLifecycleOwner, Observer {it
                it?.let {listOfPhotos->
                    progress.visibility = View.GONE
                    photosAdapter.addPhotos(listOfPhotos)
                }
        })
    }

}
