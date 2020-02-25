package com.ajithvgiri.nasa.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.ajithvgiri.nasa.R
import com.ajithvgiri.nasa.adapter.PhotosAdapter
import kotlinx.android.synthetic.main.fragment_photos.*

class PhotosFragment : Fragment(),OnItemClickListener {

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
        photosAdapter = PhotosAdapter(this)
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

    override fun getItemImage(view: View,position: Int) {
        val extras = FragmentNavigator.Extras.Builder().addSharedElement(view as ImageView, "title_${position}").build()
        //val action = PhotosFragmentDirections.actionHomeFragmentToHomeSecondFragment("From PhotosFragment $position")
        val action = PhotosFragmentDirections.actionHomeFragmentToDashboardFragment(position)
        NavHostFragment.findNavController(this@PhotosFragment).navigate(action,extras)
    }

}
