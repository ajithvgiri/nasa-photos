package com.ajithvgiri.nasa.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ajithvgiri.nasa.R
import com.ajithvgiri.nasa.adapter.ImagePagerAdapter
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var imagePagerAdapter: ImagePagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imagePagerAdapter = ImagePagerAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewpager.adapter = imagePagerAdapter
    }
}
