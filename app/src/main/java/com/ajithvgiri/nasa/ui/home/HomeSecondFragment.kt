package com.ajithvgiri.nasa.ui.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.ViewCompat

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater

import com.ajithvgiri.nasa.R
import kotlinx.android.synthetic.main.fragment_home_second.*

class HomeSecondFragment : Fragment() {

    private val args: HomeSecondFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.textview_home_second).text =
            getString(R.string.hello_home_second, args.myArg)

        view.findViewById<Button>(R.id.button_home_second).setOnClickListener {
            findNavController().navigate(R.id.action_HomeSecondFragment_to_HomeFragment)
        }

        ViewCompat.setTransitionName(textview_home_second, "title_${args.myArg}")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textview_home_second.transitionName = "title_${args.myArg}"
        }
    }
}
