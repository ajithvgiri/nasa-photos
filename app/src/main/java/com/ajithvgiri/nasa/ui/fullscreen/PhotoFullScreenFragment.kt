package com.ajithvgiri.nasa.ui.fullscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.ajithvgiri.nasa.R
import com.ajithvgiri.nasa.adapter.PhotoPagerAdapter
import com.ajithvgiri.nasa.ui.MainActivity
import com.ajithvgiri.nasa.ui.home.PhotosViewModel
import kotlinx.android.synthetic.main.fragment_photo_fullscreen.*

class PhotoFullScreenFragment : Fragment() {

    private val args: PhotoFullScreenFragmentArgs by navArgs()

    private lateinit var photosViewModel: PhotosViewModel
    private lateinit var photoPagerAdapter: PhotoPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        photoPagerAdapter = PhotoPagerAdapter()
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        photosViewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)
        return inflater.inflate(R.layout.fragment_photo_fullscreen, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewpager.adapter = photoPagerAdapter

        (activity as MainActivity).supportActionBar?.title = ""
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

        photosViewModel.listOfPhotos.observe(viewLifecycleOwner, Observer {
            it?.let {
                photoPagerAdapter.addPhotos(it)
                viewpager.setCurrentItem(args.position,false)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (android.R.id.home == item.itemId){
            NavHostFragment.findNavController(this).popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }
}
