package com.ajithvgiri.nasa.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.ajithvgiri.nasa.R
import com.ajithvgiri.nasa.adapter.ImageGridAdapter
import kotlinx.android.synthetic.main.fragment_image_grid.*

class ImageGridFragment : Fragment(), OnItemClickListener {

    private lateinit var imageGridViewModel: ImageGridViewModel
    private lateinit var imageGridAdapter: ImageGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        imageGridViewModel = ViewModelProvider(this).get(ImageGridViewModel::class.java)
        return inflater.inflate(R.layout.fragment_image_grid, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageGridAdapter = ImageGridAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GridLayoutManager(context, 2).apply {
            recyclerView.layoutManager = this
            recyclerView.adapter = imageGridAdapter
        }
    }

    override fun getItemImage(view: View,position: Int) {
        val extras = FragmentNavigator.Extras.Builder().addSharedElement(view as TextView, "title_${position}").build()
        val action = ImageGridFragmentDirections.actionHomeFragmentToHomeSecondFragment("From ImageGridFragment $position")
        NavHostFragment.findNavController(this@ImageGridFragment).navigate(action,extras)
    }
}
